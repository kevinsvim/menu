<template>
<div style="width: 50vw; height: 30vh; background: #CCCCCC; border-radius: 10px">
<div style="display: flex">
  <div style="flex: 1; text-align: left">1</div>
  <div style="flex: 1; text-align: right">2</div>
  <div style="flex: 1; text-align: left">3</div>
  <div style="flex: 1; text-align: right">4</div>
</div>
</div>
</template>

<script>
export default {
  name: 'MenuTable',
  setup(props) {
    // 解构数据
    const { areaWidth, areaHeight } = props

    let data = [
      {
        'name': '1',
        'dosage': ''
      }
    ]
    return {
      data
    }
  },

}
</script>

<style scoped>

</style>
