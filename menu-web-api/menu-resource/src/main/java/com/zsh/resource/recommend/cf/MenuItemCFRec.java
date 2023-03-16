package com.zsh.resource.recommend.cf;

/**
 * Created by zsh on 2023/3/12
 * 基于物品的协同过滤推荐算法
 *
 * @author zsh
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.io.*;

public class MenuItemCFRec {

    // 用户 - 物品评分矩阵
    private final Map<String, Map<String, Double>> data;
    // 训练集
    private final Map<String, Map<String, Double>> trainData;
    // 测试集
    private final Map<String, Map<String, Double>> testData;
    // 物品相似度矩阵
    private Map<String, Map<String, Double>> items_sim;
    // 推荐结果列表长度
    private final int N = 10;
    // 相似度矩阵文件路径
    private final String sim_file = "data/item_sim.txt";

    private final int ratio;

    public MenuItemCFRec(String datafile, int ratio) {
        data = new HashMap<>();
        trainData = new HashMap<>();
        testData = new HashMap<>();
        items_sim = new HashMap<>();
        this.ratio = ratio;
        loadData(datafile);
        items_sim = itemSimilarityBest();
    }

    /**
     * 加载数据
     * @param datafile 文件路径
     */
    private void loadData(String datafile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(datafile));
            String line;
            // 遍历文件每一行
            while ((line = br.readLine()) != null) {
                // 用户id::菜单id::评分::时间戳
                String[] parts = line.split("::");
                String user = parts[0];
                String item = parts[1];
                Double score = Double.parseDouble(parts[2]);
                // 分割训练集和测试集
                if (Math.random() * 10 < ratio) {
                    testData.putIfAbsent(user, new HashMap<>());
                    testData.get(user).put(item, score);
                } else {
                    trainData.putIfAbsent(user, new HashMap<>());
                    trainData.get(user).put(item, score);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算物品之间的相似度
     */
    private Map<String, Map<String, Double>> itemSimilarityBest() {
        System.out.println("开始计算物品之间的相似度");
        Map<String, Map<String, Double>> itemSim = new HashMap<>();
        // 得到每个物品有多少用户产生过行为
        Map<String, Integer> itemUserCount = new HashMap<>();
        // 共现矩阵
        Map<String, Map<String, Double>> count = new HashMap<>();
        //
        for (Map.Entry<String, Map<String, Double>> entry : trainData.entrySet()) {
            String user = entry.getKey();
            Map<String, Double> item = entry.getValue();
            System.out.println("user is " + user);
            for (Map.Entry<String, Double> itemEntry : item.entrySet()) {
                String i = itemEntry.getKey();
                itemUserCount.putIfAbsent(i, 0);
                if (trainData.get(user).get(i) > 0.0) {
                    itemUserCount.put(i, itemUserCount.get(i) + 1);
                }
                for (Map.Entry<String, Double> jEntry : item.entrySet()) {
                    String j = jEntry.getKey();
                    count.putIfAbsent(i, new HashMap<>());
                    count.get(i).putIfAbsent(j, 0.0);
                    if (trainData.get(user).get(i) > 0.0 && trainData.get(user).get(j) > 0.0 && !i.equals(j)) {
                        count.get(i).put(j, count.get(i).get(j) + 1);
                    }
                }
            }
        }
        // 共现矩阵 -> 相似度矩阵
        for (Map.Entry<String, Map<String, Double>> entry : count.entrySet()) {
            String i = entry.getKey();
            itemSim.putIfAbsent(i, new HashMap<>());
            for (Map.Entry<String, Double> relatedItemsEntry : entry.getValue().entrySet()) {
                String j = relatedItemsEntry.getKey();
                Double cuv = relatedItemsEntry.getValue();
                itemSim.get(i).putIfAbsent(j, 0.0);
                itemSim.get(i).put(j, cuv / Math.sqrt(itemUserCount.get(i) * itemUserCount.get(j)));
            }
        }
        // 将物品相似度矩阵保存到文件中
        try {
            File file = new File("E:\\itemSim.json");
            if (!file.exists()) {
                if(!file.createNewFile()) {
                    return itemSim;
                }
            }
            FileWriter writer = new FileWriter(file);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(itemSim, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemSim;
    }

    /**
     * 为用户进行推荐
     * @param user 用户
     * @param k    k个临近物品
     * @param nItems 总共返回n个物品
     * @return { 菜单id:最终得分 }
     */
    Map<String, Double> recommend(String user, int k, int nItems) {
        Map<String, Double> result = new HashMap<>();
        Map<String, Double> u_items = trainData.getOrDefault(user, new HashMap<>());
        for (Map.Entry<String, Double> entry : u_items.entrySet()) {
            String i = entry.getKey();
            double pi = entry.getValue();
            List<Map.Entry<String, Double>> simList = new ArrayList<>(items_sim.getOrDefault(i, new HashMap<>()).entrySet());
            simList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
            for (int j = 0; j < k && j < simList.size(); j++) {
                String item = simList.get(j).getKey();
                double wj = simList.get(j).getValue();
                if (u_items.containsKey(item)) {
                    continue;
                }
                result.merge(item, pi * wj, Double::sum);
            }
        }
        List<Map.Entry<String, Double>> resultList = new ArrayList<>(result.entrySet());
        resultList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        Map<String, Double> res = new LinkedHashMap<>();
        for (int i = 0; i < nItems && i < resultList.size(); i++) {
            res.put(resultList.get(i).getKey(), resultList.get(i).getValue());
        }
        return res;
    }



    /**
     * 计算准确率
     */
    public double precision(int k, int nItems) {
        System.out.println("开始计算准确率 ...");
        int hit = 0;
        double precision = 0;
        for (String user : this.testData.keySet()) {
            Map<String, Double> u_items = this.testData.get(user);
            Map<String, Double> result = this.recommend(user, k, nItems);
            for (Map.Entry<String, Double> entry : result.entrySet()) {
                String item = entry.getKey();
                if (u_items.containsKey(item)) {
                    hit += 1;
                }
            }
            precision += nItems;
        }
        return (double) hit / (precision);
    }

    public static void main(String[] args) {
        MenuItemCFRec ib = new MenuItemCFRec("D:\\recommend_system\\代码\\7-chapter\\data\\ratings.dat", 5);
        System.out.printf("用户1进行推荐的结果如下：%s\n", ib.recommend("1", 8, 40));
        System.out.printf("准确率为：%f\n", ib.precision(8, 40));
    }

}

