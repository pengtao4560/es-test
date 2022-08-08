package Algorithm.src.com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
 */
public class TanxinSF {

    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
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

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义一个 bestKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果 bestKey 不为null , 则会加入到 selects
        String bestKey = null;
        while(allAreas.size() != 0) { // 如果allAreas 不为0, 则表示还没有覆盖到所有的地区
            //每进行一次while,需要
            bestKey = null;

            int bestSize = 0; // 最好的比较到的交集的长度（贪心算法的核心思想）
            //遍历 broadcasts, 取出对应key
            for(String key : broadcasts.keySet()) {
                //每进行一次for
                tempSet.clear();
                //areas 就是当前这个key能够覆盖的地区
                // areas 就是 广播覆盖图例的：k1 或 k2 或 k3 或 k4 或K5
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出 tempSet 和   allAreas 集合的交集, 交集会赋给 tempSet
                //
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                // tempSet.size() >broadcasts.get(bestKey).size()) 体现出贪心算法的特点,每次都选择最优的
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
                allAreas.removeAll(broadcasts.get(bestKey));
            }

        }

        System.out.println("result: " + selects);//[K1,K2,K3,K5]



    }
}
