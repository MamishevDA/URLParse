/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.regex.Pattern;

/**
 *
 * @author dmitriy.mamishev
 */
public class a1 {

    public static void main(String[] args) {
        System.out.println(a1.reverseWords("Hello world"));
        System.out.println(2|1);
        Integer i = 42; 
    String s = (i < 40) ? "life" : (i > 50) ? "universe" : "everything"; 
    System.out.print(s); 
    }

    public static String reverseWords(String s) {
        /*
          Assume that the parameter String can only contain spaces and alphanumeric characters.
          Please implement this method to
          reverse each word in the original String while maintaining the word order.
          For example:
          parameter: "Hello world", result: "olleH dlrow"
         */

        Pattern pattern = Pattern.compile("[ ]{1,}");
        String str[] = pattern.split(s);
        StringBuilder sb = new StringBuilder();
        for (String word : str) {
            for (int i = word.length() - 1; i >= 0; i--) {
                sb.append(word.charAt(i));
            }
            sb.append(" ");
        }

        return sb.toString();
    }

}
