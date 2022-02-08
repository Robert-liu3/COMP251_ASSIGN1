public class Main {
    public static void main(String[] args) {
        String str = "David no no no no nobody never";
        System.out.println(str);
        String[] splitter = str.split(" ");
        for (int i = 0; i < splitter.length; i++) {
            System.out.println(splitter);
        }
    }
}
