
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dmitriy.mamishev
 */
public class T1 {

    public static int getSumOfNumbers(String s) {
        /*
          Please implement this method to
          return the sum of all integers found in the parameter String. You can assume that
          integers are separated from other parts with one or more spaces (' ' symbol).
          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
         */
        Pattern pattern = Pattern.compile("[ ]{1,}");
        String[] res = pattern.split(s);
        int sum = 0;
        for (String re : res) {
            try {
                sum += Integer.parseInt(re);
            } catch (NumberFormatException  e) {
            }

        } return sum;
    
}
    public static void main(String[] args) {
        System.out.println(T1.getSumOfNumbers("12 some text 3  7"));
    }
}