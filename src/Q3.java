import java.util.*;

public class Q3 {
    public static int k = 0;
    public static int[] rank;
    public static HashMap<String, HashMap<String, Integer>> postPerPerson = new HashMap<String, HashMap<String, Integer>>();
    public static HashMap<String, Integer> numPerWord = new HashMap<String, Integer>();

    //public static HashMap<String, Integer> map = new HashMap<String, Integer>();
    public String[] post;

    public static ArrayList<String> Discussion_Board(String[] posts){
        ArrayList<String> output = new ArrayList<String>();
        String personPost;
        for (int i = 0; i < posts.length; i++) {
            personPost = posts[i];
            String[] splitter = personPost.split(" ");
            String name = splitter[0];
            /* If there isn't a key with the person's name */
            if (!postPerPerson.containsKey(name)) {
                HashMap<String, Integer> map = new HashMap<String, Integer>();
                for (int j = 1; j < splitter.length; j++) {
                    if (!map.containsKey(splitter[j])) {
                        map.put(splitter[j], 1);
                    }
                    else {
                        map.replace(splitter[j], map.get(splitter[j]) +1);
                    }
                    postPerPerson.put(name, map);
                }
            }
            else {
                /* extracting the hashmap with the person's name */
                HashMap<String, Integer> map = postPerPerson.get(name);
                for (int k = 1; k < splitter.length; k++) {
                    if (!map.containsKey(splitter[k])) {
                        map.put(splitter[k], 1);
                    }
                    else {
                        map.replace(splitter[k], map.get(splitter[k]) +1);
                    }
                }
            }
        }
        if (!postPerPerson.isEmpty()) {
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
            Map<String, Integer> treeMap = new TreeMap<>(numPerWord);

            ArrayList<Map.Entry<String,Integer>> toBeSorted = new ArrayList<Map.Entry<String,Integer>>(treeMap.entrySet());
            toBeSorted.sort(Collections.reverseOrder(Comparator.comparingInt(Map.Entry::getValue)));

            for (Map.Entry<String,Integer> s : toBeSorted) {
                output.add(s.getKey());
            }
        }
        return output;
    }
}
