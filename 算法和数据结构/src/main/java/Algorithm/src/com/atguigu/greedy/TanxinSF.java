package Algorithm.src.com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * ̰���㷨
 *
 *
 * k1     ���� �Ϻ� ���
 * k2     ���� ���� ����
 * k3     �ɶ� �Ϻ� ����
 * k4     �Ϻ� ���
 * k5     ���� ����
 *
 * ���� �Ϻ� ��� ����  ���� �ɶ�   ���� ����
 * K1 K2 K3 K5
 */
public class TanxinSF {

    public static void main(String[] args) {
        //�����㲥��̨,���뵽Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //��������̨���뵽broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("����");
        hashSet1.add("�Ϻ�");
        hashSet1.add("���");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("����");
        hashSet2.add("����");
        hashSet2.add("����");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("�ɶ�");
        hashSet3.add("�Ϻ�");
        hashSet3.add("����");


        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("�Ϻ�");
        hashSet4.add("���");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("����");
        hashSet5.add("����");

        //���뵽map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas ������еĵ���
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("����");
        allAreas.add("�Ϻ�");
        allAreas.add("���");
        allAreas.add("����");
        allAreas.add("����");
        allAreas.add("�ɶ�");
        allAreas.add("����");
        allAreas.add("����");

        //����ArrayList, ���ѡ��ĵ�̨����
        ArrayList<String> selects = new ArrayList<String>();

        //����һ����ʱ�ļ��ϣ� �ڱ����Ĺ����У���ű��������еĵ�̨���ǵĵ����͵�ǰ��û�и��ǵĵ����Ľ���
        HashSet<String> tempSet = new HashSet<String>();

        //����һ�� bestKey �� ������һ�α��������У��ܹ��������δ���ǵĵ�����Ӧ�ĵ�̨��key
        //��� bestKey ��Ϊnull , �����뵽 selects
        String bestKey = null;
        while(allAreas.size() != 0) { // ���allAreas ��Ϊ0, ���ʾ��û�и��ǵ����еĵ���
            //ÿ����һ��while,��Ҫ
            bestKey = null;

            int bestSize = 0; // ��õıȽϵ��Ľ����ĳ��ȣ�̰���㷨�ĺ���˼�룩
            //���� broadcasts, ȡ����Ӧkey
            for(String key : broadcasts.keySet()) {
                //ÿ����һ��for
                tempSet.clear();
                //areas ���ǵ�ǰ���key�ܹ����ǵĵ���
                // areas ���� �㲥����ͼ���ģ�k1 �� k2 �� k3 �� k4 ��K5
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //��� tempSet ��   allAreas ���ϵĽ���, �����ḳ�� tempSet
                //
                tempSet.retainAll(allAreas);
                //�����ǰ������ϰ�����δ���ǵ�������������maxKeyָ��ļ��ϵ�������
                //����Ҫ����maxKey
                // tempSet.size() >broadcasts.get(bestKey).size()) ���ֳ�̰���㷨���ص�,ÿ�ζ�ѡ�����ŵ�
                if (tempSet.size() > 0 &&
                        (bestKey == null || tempSet.size() > bestSize)) {
                    bestKey = key; // ��k1-k4����ѡ ��õ�
                    bestSize = tempSet.size();
                }
            }
            //bestKey != null, ��Ӧ�ý� bestKey ���� selects
            if(bestKey != null) {
                selects.add(bestKey);
                //��maxKeyָ��Ĺ㲥��̨���ǵĵ������� allAreas ȥ��
                allAreas.removeAll(broadcasts.get(bestKey));
            }

        }

        System.out.println("result: " + selects);//[K1,K2,K3,K5]



    }
}
