<!--  -->
<template>
  <div>
    <el-switch v-model="draggable" active-text="开启拖拽" inactive-text="关闭拖拽"></el-switch>
    <el-button v-if="draggable" type="primary" v-on:click="batchSave" round>批量保存</el-button>
    <el-button type="danger" @click="batchDelete">批量删除</el-button>
    <el-tree
      :data="menus"
      :props="defaultProps"
      :show-checkbox="true"
      node-key="id"
      :default-expand-all="false"
      :expand-on-click-node="false"
      :default-expanded-keys="expandedKeys"
      :draggable="draggable"
      :allow-drop="allowDrop"
      @node-drop="handleDrop"
      ref="menuTree"
    >
      <!--使用卡槽机制slot-scope，获取节点的信息。node: 当前节点（包括节点的各种属性值，以及填充的后台数据）  data：后台传递的节点数据 -->
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <!--一级和二级菜单才有增加按钮，没有子菜单的才可以删除，所有菜单都可以修改-->
          <el-button v-if="node.level <= 2" type="text" size="mini" @click="() => append(data)">增加</el-button>
          <!-- 也可以这样写事件： @click="edit(data)  或者  @click="() => append(data) -->
          <el-button type="text" size="mini" @click="edit(data)">修改</el-button>
          <el-button
            v-if="node.childNodes.length === 0"
            type="text"
            size="mini"
            @click="() => remove(node, data)"
          >删除</el-button>
        </span>
      </span>
    </el-tree>

    <!--弹框  -->
    <el-dialog title="提示" :visible.sync="dialogVisible" width="40%" :close-on-click-modal="false">
      <el-form :model="category">
        <el-form-item label="分类名称">
          <el-input v-model="category.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pCid: [],
      updateNodes: [],
      maxLevel: 0,
      draggable: false,
      dialogType: "", //区分添加弹框add还是修改弹框edit
      title: "", //针对不同的弹框显示不同的标题
      category: {
        name: "",
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        icon: "",
        productUnit: "",
      },
      dialogVisible: false, //为true时候显示弹框
      menus: [],
      expandedKeys: [],
      defaultProps: {
        children: "childrens", //子节点的变量名
        label: "name", //节点显示的名称变量名，与后台变量名称一致
      },
    };
  },
  methods: {
    // 获取数据列表
    getMenus() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/product/category/list/tree"),
        method: "get", //get请求传递数据的时候，默认合并默认的参数值。起封装类在src/utils/httpRequest.js中。
      }).then(({data}) => {
        console.log("成功了！！", data.data);
        this.menus = data.data;
      });
    },

    //批量删除分类
    batchDelete() {
      //使用组件自带的方法，声明组件名字ref="名字"，this.$refs.名字.方法
      let checkedNodes = this.$refs.menuTree.getCheckedNodes(); //返回选中的节点的data数据
      let catIds = [];
      for (let i = 0; i < checkedNodes.length; i++) {
        let catId = checkedNodes[i].catId;
        catIds.push(catId);
      }
      this.$confirm(`是否将【${catIds}】分类删除`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //确定按钮
          //封装好了的http方法。
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(catIds, false), //adornData(传递的数据, false：表示不合并默认数据)
          }).then(({data}) => {
            //发送请求之后调用成功
            this.$message({
              type: "success",
              message: "分类批量删除成功!",
            });
            //更新一下页面，并展示删除节点的父节点
            this.getMenus();
          });
        })
        .catch(() => {
          //取消按钮
          this.$message({
            type: "info",
            message: "已取消批量删除",
          });
        });

    },

    //拖拽成功的时候触发的事件handleDrop(拖拽节点，目的几点，相对于目的节点的位置，ev)
    handleDrop(draggingNode, dropNode, dropType, ev) {
      console.log("tree drop: ", draggingNode, dropNode, dropType, ev);

      //创建传递后台的对象，并将移动的每一个节点的数据封装成数组，传递到后台。节点移动后parentCid，catLevel，sort 其余的都不会改变
      //1. 获取父类id
      let pCid = 0; //更新后父id
      let siblings = null; //拖拽完成之后，所在父节点的所有子节点
      if (dropType == "before" || dropType == "after") {
        //考虑一级节点没父节点的情况
        pCid =
          dropNode.parent.data.catId == undefined
            ? 0
            : dropNode.parent.data.catId;
        siblings = dropNode.parent.childNodes;
      } else {
        pCid = dropNode.data.catId;
        siblings = dropNode.childNodes;
      }
      this.pCid.push(pCid); //将修改的节点的父节点存起来，用来保存成功后的展示
      //2.获取最新排序和层级（遍历拖拽节点的父节点所有子节点）
      for (let i = 0; i < siblings.length; i++) {
        //node几点信息随着节点的移动，其属性所在的层级随之发生改变，但是数据库中的信息不会发生改变。
        //所以只要将数据库中的层级catLevel与node节点level同步信息就可以。
        if (siblings[i].data.catId == draggingNode.data.catId) {
          //遍历到拖拽节点
          let catLevel = draggingNode.level;
          if (siblings[i].level != draggingNode.level) {
            //移动后层级发生变化
            catLevel = siblings[i].level;

            //其所有的子节点也要同步
            this.updateChildNodeLevel(siblings[i]);
          }
          //传递到后台的对象
          this.updateNodes.push({
            catId: siblings[i].data.catId,
            parentCid: pCid,
            sort: i,
            catLevel: catLevel,
          });
        } else {
          this.updateNodes.push({
            catId: siblings[i].data.catId,
            sort: i,
          });
        }
      }

      console.log("提交后台的数据：", this.updateNodes);
    },
    //  同步其子节点的层级数据
    updateChildNodeLevel(node) {
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          //子节点只是变了层级，父节点id和sort都没变。
          this.updateNodes.push({
            catId: node.childNodes[i].data.catId,
            catLevel: node.childNodes[i].level,
          });
          this.updateChildNodeLevel(node.childNodes[i]);
        }
      }
    },
    //批量保存
    batchSave() {
      //首先 创建传递后台的对象，节点移动后parentCid，catLevel，sort 其余的都不会改变。
      //思路：将拖拽的每一个节点所更新的数据保存到一个数组中，最后批量保存将数组传递到后台。
      // 需要一个在拖拽成功的时候，触发的一个事件。node-drop 事件

      this.$http({
        url: this.$http.adornUrl("/product/category/update/batchSave"),
        method: "post",
        data: this.$http.adornData(this.updateNodes, false),
      }).then(({data}) => {
        this.$message({
          type: "success",
          message: "批量保存成功!",
        });

        //更新一下页面，并展示新增节点的父节点
        this.getMenus();
        this.expandedKeys = this.pCid;
        //这地方一定要置为空！！！！
        this.updateNodes = [];
        this.maxLevel = 0;
      });
    },

    //进行菜单拖拽功能的判定
    allowDrop(draggingNode, dropNode, type) {
      //被拖动的当前节点以及所在的父节点总层数不能大于3
      //1）、被拖动的当前节点总层数
      console.log("allowDrop:", draggingNode, dropNode, type);

      //递归查询该节点最大深度
      this.countNodeLevel(draggingNode);
      console.log("最大深度：", this.maxLevel);
      // //当前正在拖动的节点+父节点所在的深度不大于3即可
      let deep = Math.abs(this.maxLevel - draggingNode.level) + 1; //以该节点为起点的层数

      //这地方一定要置为0，否则将会出现错误！！！！大坑！！！！！！！！！
      this.maxLevel = 0;
      if (type === "inner") {
        // console.log(
        //   `this.maxLevel：${this.maxLevel}；draggingNode.data.catLevel：${draggingNode.data.catLevel}；dropNode.level：${dropNode.level}`
        // );
        return deep + dropNode.level <= 3;
      } else {
        return deep + dropNode.parent.level <= 3;
      }
    },
    //找到子节点最大的深度
    countNodeLevel(node) {
      //找到所有子节点，求出最大深度（node中有一个变量参数为data，是数据库返回的数据。其余的参数还有prent父节点，level层数，这个随着节点的拖拽进行改变，但是数据库中的数据不发生改变。）
      if (node.childNodes != null && node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          if (node.childNodes[i].level > this.maxLevel) {
            this.maxLevel = node.childNodes[i].level;
          }
          this.countNodeLevel(node.childNodes[i]);
        }
      }
    },

    //添加节点，显示弹框
    append(data) {
      console.log("增加数据", data);
      this.category.name = "";
      this.category.parentCid = data.catId; //增加节点为该节点的子节点，将该节点id是其子节点的父id
      this.category.catLevel = data.catLevel * 1 + 1; //避免是字符串，先乘以1，转换成数字
      this.category.showStatus = 1;
      this.category.sort = 0;
      this.category.icon = "";
      this.category.productUnit = "";
      this.dialogType = "add";
      this.title = "添加分类";
      this.dialogVisible = true; //显示弹框
    },

    //修改节点，显示弹框
    edit(data) {
      console.log("要修改的数据：", data);
      this.dialogType = "edit";
      this.title = "修改分类";
      this.dialogVisible = true; //显示弹框

      //当一人在某一时间的页面修改之后，另在某一时间之前修改，要获取最新数据，重新向后台发送请求。
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: "get",
      }).then(({data}) => {
        //{data},如果不解构的话，返回的信息包括头信息，配置信息，以及后台数据R对象。解构之后直接获得的R对象，因为前台名字为{data:{R对象的json},....}
        //请求成功，要回显的数据
        console.log("回显数据：", data);
        this.category.catId = data.category.catId;
        this.category.name = data.category.name;
        this.category.parentCid = data.category.parentCid;
        this.category.catLevel = data.category.catLevel;
        this.category.showStatus = data.category.showStatus;
        this.category.sort = data.category.sort;
        this.category.icon = data.category.icon;
        this.category.productUnit = data.category.productUnit;
      });
    },

    //判断是添加还是修改
    submitData() {
      if (this.dialogType == "add") {
        this.addCategory();
      } else if (this.dialogType == "edit") {
        this.editCategory();
      }
    },

    //添加分类
    addCategory() {
      this.$http({
        url: this.$http.adornUrl("/product/category/save"),
        method: "post",
        data: this.$http.adornData(this.category, false),
      }).then(({data}) => {
        this.$message({
          type: "success",
          message: "分类添加成功!",
        });
        //关闭弹框
        this.dialogVisible = false;
        //更新一下页面，并展示新增节点的父节点
        this.getMenus();
        this.expandedKeys = [this.category.parentCid];
      });
    },

    //修改分类
    editCategory() {
      //为了提高效率，将不修改的字段不需要传到后台，mybatis plus 将为空的字段sql语句会去掉。
      //使用es6结构方法
      var {catId, name, icon, productUnit} = this.category;
      this.$http({
        url: this.$http.adornUrl("/product/category/update"),
        method: "post",
        //this.$http.adornData({catId:catId, name:name, icon:icon, productUnit:productUnit}, false) es6写法==》
        data: this.$http.adornData({catId, name, icon, productUnit}, false),
      }).then(({data}) => {
        this.$message({
          type: "success",
          message: "分类修改成功!",
        });
        //关闭弹框
        this.dialogVisible = false;
        //更新一下页面，并展示修改节点的父节点
        this.getMenus();
        this.expandedKeys = [this.category.parentCid];
      });
    },

    //删除节点
    remove(node, data) {
      //发送给后端的数据，后端是数组类型
      var ids = [data.catId];
      this.$confirm(`是否将【${data.name}】删除`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //确定按钮
          //封装好了的http方法。
          this.$http({
            url: this.$http.adornUrl("/product/category/delete"),
            method: "post",
            data: this.$http.adornData(ids, false), //adornData(传递的数据, false：表示不合并默认数据)
          }).then(({data}) => {
            //发送请求之后调用成功
            this.$message({
              type: "success",
              message: "分类删除成功!",
            });
            //更新一下页面，并展示删除节点的父节点
            this.getMenus();
            this.expandedKeys = [node.parent.data.catId];
          });
        })
        .catch(() => {
          //取消按钮
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },

  //周期函数 vue对象创建成功后调用
  created: function () {
    this.getMenus();
  },
};
</script>

<style scoped>
</style>

