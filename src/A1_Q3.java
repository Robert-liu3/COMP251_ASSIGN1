import java.lang.reflect.Array;
import java.util.*;

public class A1_Q3 {

	public static int k = 0;
	public static int[] rank;
	public static HashMap<String, HashMap<String, Integer>> postPerPerson = new HashMap<String, HashMap<String, Integer>>();
	public static HashMap<String, Integer> numPerWord = new HashMap<String, Integer>();

	//public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public String[] post;
	
	public static ArrayList<String> Discussion_Board(String[] posts){
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<String> random = new ArrayList<String>();
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
			output = sorter();
			random = random();
		}
		return output;
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
		//creating iterator for each word
		//Iterator allWords = map.entrySet().iterator();
		//creating iterator for each person
		//Iterator allPersons = postPerPerson.entrySet().iterator();

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

		Map<String, Integer> treeMap = new TreeMap<>(numPerWord);

		ArrayList<Map.Entry<String,Integer>> toBeSorted = new ArrayList<Map.Entry<String,Integer>>(treeMap.entrySet());

		toBeSorted.sort(Collections.reverseOrder(Comparator.comparingInt(Map.Entry::getValue)));

		ArrayList<String> output = new ArrayList<String>();
		for (Map.Entry<String,Integer> s : toBeSorted) {
			String a = s.getKey().toString();
			output.add(a);
		}
		return output;
	}
	public static ArrayList<String> random() {
		ArrayList<String> rando = new ArrayList<String>();
		rando.add("no");
		rando.add("nobody");
		rando.add("never");
		return rando;
	}
//	public static Integer getKeys(String word) {
//		for ( Integer key : words.keySet() ) {
//			if (words.get(key).equals(word)) {
//				return key;
//			}
//		}
//		return -1;
//	}

}
