import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class Main {
    @Test
    public void test1() {
        String david = "David no no no no nobody never";
        String jennifer = "Jennifer never never nobody no";
        String parham = "Parham no not never nobody";
        String shirshir = "Shishir no never know nobody";
        String alvin = "Alvin why no nobody";
        String alvin2 = "Alvin nobody never know why nobody";
        String david2 = "David never no nobody";
        String jennifer2 = "Jennifer why ever not";
        String[] a = {david,jennifer, parham, shirshir, alvin, alvin2, david2, jennifer2};
        A1_Q3 q3 = new A1_Q3();
        ArrayList<String> answer = q3.Discussion_Board(a);
        System.out.println(q3.Discussion_Board(a));
        assertEquals(3, answer.size());
    }
    public static void main(String[] args) {
        String james = "James gobble de gook";
        String bill = "Bill gobble";
        String james2 = "james de gook";


        String user1 = "user1 doubledutch double double dutch";
        String user2 = "user2 dutch doubledutch doubledutch double";
        String user3 = "user3 not double dutch doubledutch";


        String david = "David no no no no nobody never";
        String jennifer = "Jennifer never never nobody no";
        String parham = "Parham no not never nobody";
        String shirshir = "Shishir no never know nobody";
        String alvin = "Alvin why no nobody";
        String alvin2 = "Alvin nobody never know why nobody";
        String david2 = "David never no nobody";
        String jennifer2 = "Jennifer why ever not";

        String[] a = {david,jennifer, parham, shirshir, alvin, alvin2, david2, jennifer2};
        String[] b = {user1, user2, user3};
        String[] d = {james, bill, james2};

        A1_Q3 q3 = new A1_Q3();
        //ArrayList<String> answer = q3.Discussion_Board(a);
        ArrayList<String> c = q3.Discussion_Board(a);
        System.out.println(c);
        ArrayList<String> c2 = q3.Discussion_Board(b);
        System.out.println(c2);

    }
}
