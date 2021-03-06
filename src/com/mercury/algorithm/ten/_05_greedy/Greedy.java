package com.mercury.algorithm.ten._05_greedy;

import java.util.*;

/**
 * 贪心算法
 * <p>
 * 问题描述： 选择最少的广播台，让所有的地区都能收到信号
 */
public class Greedy {

    public static void main(String[] args) {
        // 广播电台和广播电台对应覆盖地区集合
        Map<String, HashSet<String>> broadcasts = initBroadCasts();
        // 地区集合,不能重复
        Set<String> allAreas = initAllAreas();

        List<String> less = chooseLessBroadCast(broadcasts, allAreas);
        System.out.println(less.toString());

    }

    /**
     * @param broadCasts 所有的广播台对应能接收到信号的地区
     * @param allAreas  所有的地区
     * @return 最少广播台集合 ==> K1,K2等
     */
    public static List<String> chooseLessBroadCast(Map<String, HashSet<String>> broadCasts, Set<String> allAreas) {
        List<String> less = new ArrayList<>();
        HashSet<String> tempSet = new HashSet<>();

        String maxKey = null;
        int preMaxSize = 0;
        //1、遍历，直到所有的地区都被覆盖
        while(allAreas.size() != 0) {
            maxKey = null;
            //2、遍历广播台，找到一次循环中最多未被覆盖区域的广播台
            for (String key : broadCasts.keySet()) {
                tempSet.clear();
                HashSet<String> currentAreas = broadCasts.get(key);

                //2.1 获得当前广播台中，未被覆盖的区域集合
                tempSet.addAll(currentAreas);
                tempSet.retainAll(allAreas);

                //2.2 判断当前广播台，是否为最多未被覆盖区域的
                // maxKey == null ==>第一次进来
                // 如果和所有地区的重合部分，比上一次的大，说明要重置maxKey
                if(maxKey == null || tempSet.size() > preMaxSize) {
                    maxKey = key;
                    preMaxSize = tempSet.size(); // 记住当前最大的地区长度
                }
            }
            // 3、一次遍历完所有广播台后，得到最多未被覆盖区域的广播台 ==>从所有区域中去掉这些区域
            if(maxKey != null) {
                less.add(maxKey);
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }

        return less;
    }

    private static Set<String> initAllAreas() {
        Set<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        return allAreas;
    }

    private static Map<String, HashSet<String>> initBroadCasts() {
        Map<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);
        return broadcasts;
    }



}