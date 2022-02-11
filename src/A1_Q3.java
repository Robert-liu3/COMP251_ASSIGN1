import java.lang.reflect.Array;
import java.util.*;

public class A1_Q3 {

	private static HashMap<String, HashMap<String, Integer>> postPerPerson = new HashMap<String, HashMap<String, Integer>>();

	private static HashMap<String, Integer> numPerWord = new HashMap<String, Integer>();

	public static ArrayList<String> Discussion_Board(String[] posts){
		HashMap<String, Integer> newMap = new HashMap<String, Integer>();
		String personPost;
		for (int i = 0; i < posts.length; i++) {
			personPost = posts[i];
			String[] splitter = personPost.split(" ");
			String name = splitter[0];
			/* If there isn't a key with the person's name */
			if (!postPerPerson.containsKey(name)) {
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				createNewMap(splitter, name, map);
			}
			else {
				/* extracting the hashmap with the person's name */
				HashMap<String, Integer> map = postPerPerson.get(name);
				addToMap(splitter, map);
			}
		}
		if (!postPerPerson.isEmpty()) {
			addToNumPerWordMap();
			ArrayList<String> output = sorter();
			postPerPerson.clear();
			numPerWord.clear();
			return output;
		}
		return null;
	}

	public static void createNewMap(String[] thePost, String name, HashMap<String, Integer> map) {
		for (int j = 1; j < thePost.length; j++) {
			if (!map.containsKey(thePost[j])) {
				map.put(thePost[j], 1);
			}
			else {
				map.replace(thePost[j], map.get(thePost[j]) +1);
			}
			postPerPerson.put(name, map);
		}
	}

	public static void addToMap(String[] thePost, HashMap<String, Integer> map) {
		for (int k = 1; k < thePost.length; k++) {
			if (!map.containsKey(thePost[k])) {
				map.put(thePost[k], 1);
			}
			else {
				map.replace(thePost[k], map.get(thePost[k]) +1);
			}
		}
	}

	public static void addToNumPerWordMap() {
		//getting the FIRST person's different words
		Map.Entry<String,HashMap<String, Integer>> mapEntry = postPerPerson.entrySet().iterator().next();
		String mapEntryName = mapEntry.getKey();
		HashMap<String, Integer> map = postPerPerson.get(mapEntryName);
		//iterating through each word of ONLY THE FIRST PERSON
		for (String key : map.keySet()) {

			String wordVal = key;

			//iterating through each PERSON
			for (String name : postPerPerson.keySet()) {

				HashMap<String, Integer> personsWords = postPerPerson.get(name);

				if (personsWords.containsKey(wordVal)) {
					int num = personsWords.get(wordVal);
					if (!numPerWord.containsKey(wordVal)) {
						numPerWord.put(wordVal, num);
					} else {
						numPerWord.replace(wordVal, numPerWord.get(wordVal)+num);
					}
				} else {
					if (numPerWord.containsKey(wordVal)) {
						numPerWord.remove(wordVal);
						break;
					} else break;
				}
			}
		}
	}

	public static ArrayList<String> sorter() {
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(numPerWord);
		Map treeMapSortVal = sortingTree	(treeMap);
		ArrayList<String> output = new ArrayList<String>(treeMapSortVal.keySet());
		return output;
	}

	public static <key, val_ extends Comparable<val_> > Map<key, val_> sortingTree(final Map<key, val_> treeMap) {
		Comparator<key> compareVal = new Comparator<key>() {
			public int compare(key key_, key key_next) {
				int num = treeMap.get(key_next).compareTo(treeMap.get(key_));
				if (num == 0) {
					return 1;
				} else {
					return num;
				}
			}
		};
		Map<key, val_> sortedTreeMap = new TreeMap<key, val_>(compareVal);
		sortedTreeMap.putAll(treeMap);
		return sortedTreeMap;
	}
}
