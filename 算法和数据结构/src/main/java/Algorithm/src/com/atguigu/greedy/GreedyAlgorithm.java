package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

	public static void main(String[] args) {
		//?????????,????Map
		HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
		//?????????????broadcasts
		HashSet<String> hashSet1 = new HashSet<String>();
		hashSet1.add("????");
		hashSet1.add("???");
		hashSet1.add("???");
		
		HashSet<String> hashSet2 = new HashSet<String>();
		hashSet2.add("????");
		hashSet2.add("????");
		hashSet2.add("????");
		
		HashSet<String> hashSet3 = new HashSet<String>();
		hashSet3.add("???");
		hashSet3.add("???");
		hashSet3.add("????");
		
		
		HashSet<String> hashSet4 = new HashSet<String>();
		hashSet4.add("???");
		hashSet4.add("???");
		
		HashSet<String> hashSet5 = new HashSet<String>();
		hashSet5.add("????");
		hashSet5.add("????");
	
		//????map
		broadcasts.put("K1", hashSet1);
		broadcasts.put("K2", hashSet2);
		broadcasts.put("K3", hashSet3);
		broadcasts.put("K4", hashSet4);
		broadcasts.put("K5", hashSet5);
		
		//allAreas ??????е????
		HashSet<String> allAreas = new HashSet<String>();
		allAreas.add("????");
		allAreas.add("???");
		allAreas.add("???");
		allAreas.add("????");
		allAreas.add("????");
		allAreas.add("???");
		allAreas.add("????");
		allAreas.add("????");
		
		//????ArrayList, ?????????????
		ArrayList<String> selects = new ArrayList<String>();
		
		//???????????????? ???????????У????????????е???????????????????и????????????
		HashSet<String> tempSet = new HashSet<String>();
		
		//?????maxKey ?? ????????α????????У???????????δ?????????????????key
		//???maxKey ???null , ?????? selects
		String maxKey = null;
		while(allAreas.size() != 0) { // ???allAreas ???0, ????????и???????е????
			//????????while,???
			maxKey = null;
			
			//???? broadcasts, ??????key
			for(String key : broadcasts.keySet()) {
				//????????for
				tempSet.clear();
				//??????key???????????
				HashSet<String> areas = broadcasts.get(key);
				tempSet.addAll(areas);
				//???tempSet ??   allAreas ????????, ???????? tempSet
				tempSet.retainAll(allAreas);
				//??????????????????δ?????????????????maxKey??????????????
				//?????????maxKey
				// tempSet.size() >broadcasts.get(maxKey).size()) ???????????????,??ζ?????????
				if(tempSet.size() > 0 && 
						(maxKey == null || tempSet.size() >broadcasts.get(maxKey).size())){
					maxKey = key;
				}
			}
			//maxKey != null, ??????maxKey ????selects
			if(maxKey != null) {
				selects.add(maxKey);
				//??maxKey???????????????????? allAreas ???
				allAreas.removeAll(broadcasts.get(maxKey));
			}
			
		}
		
		System.out.println("????????????" + selects);//[K1,K2,K3,K5]
		
		
		
	}

}
