package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double total =0;

        List<String> list = new ArrayList<>();

        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        for (String s : list) {
            Pattern p = Pattern.compile("\\$(\\d+(?:\\.\\d{1,2})?)");

            Matcher m = p.matcher(s);

            while (m.find()) {
                total += Double.parseDouble(m.group(1));
            }
            System.out.println(s);
        }
        System.out.println("Total: $"+ total);
        // write your code here
    }
}
