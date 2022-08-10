package 算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * 贪心算法
 *
 *
 * k1     北京 上海 天津
 * k2     广州 北京 深圳
 * k3     成都 上海 杭州
 * k4     上海 天津
 * k5     杭州 大连
 *
 * 北京 上海 天津 广州  深圳 成都   杭州 大连
 * K1 K2 K3 K5
 *
 * k1     北京 上海 天津
 * k2     广州 北京 深圳
 * k3     成都 上海 杭州
 * k4     上海 天津
 * k5     南京
 * k6     青岛
 * [k1, k2, k3, k5, k6]
 */
public class 贪心算法 {
    static HashSet<String>  addToHashSet(String input) {
        HashSet<String> set = new HashSet<>();
        String[] s = input.split(" ");
        for (String s1 : s) {
            if (s1.trim().length() > 0) {

                set.add(s1.trim());
            }
        }
        return set;
    }
    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcastsSet = new HashMap<String, HashSet<String>>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        Scanner scanner = new Scanner(System.in);
        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();

        String k = "k";
        int i = 1;
        while (true) {
            System.out.printf("构建 %s 的城市，以空格分隔，输入exit结束", k+i);
            System.out.println();

            HashSet<String> hashSet = null;
            String result = scanner.nextLine();
            if(result.equals("exit")){
                break;
            }
            hashSet = addToHashSet(result);
            broadcastsSet.put(k+i, hashSet);
            i++;

        }
        for (String s : broadcastsSet.keySet()) {
            allAreas.addAll(broadcastsSet.get(s));
        }

        System.out.println(" allAreas " + allAreas.toString());


        //创建ArrayList, 存放选择的电台集合
        List<String> selects = new ArrayList<String>();

        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义一个 bestKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果 bestKey 不为null , 则会加入到 selects
        String bestKey = null;
        while(allAreas.size() != 0) { // 如果allAreas 不为0, 则表示还没有覆盖到所有的地区
            //每进行一次while,需要
            bestKey = null;

            int bestSize = 0; // 最好的比较到的交集的长度（贪心算法的核心思想）
            //遍历 broadcastsSet, 取出对应key
            for(String key : broadcastsSet.keySet()) {
                //每进行一次for
                tempSet.clear();
                //areas 就是当前这个key能够覆盖的地区
                // areas 就是 广播覆盖图例的：k1 或 k2 或 k3 或 k4 或K5
                HashSet<String> areas = broadcastsSet.get(key);
                tempSet.addAll(areas);
                //求出 tempSet 和   allAreas 集合的交集, 交集会赋给 tempSet
                //
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                // tempSet.size() >broadcastsSet.get(bestKey).size()) 体现出贪心算法的特点,每次都选择最优的
                if (tempSet.size() > 0 &&
                        (bestKey == null || tempSet.size() > bestSize)) {
                    bestKey = key; // 从k1-k4中挑选 最好的
                    bestSize = tempSet.size();
                }
            }
            //bestKey != null, 就应该将 bestKey 加入 selects
            if(bestKey != null) {
                selects.add(bestKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcastsSet.get(bestKey));
            }

        }

        System.out.println("结果 result: " + selects);//[K1,K2,K3,K5]

    }
}

