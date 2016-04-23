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
public class NewClass {
    public static void main(String[] args) {
        System.out.println(NewClass.countWords("asas asdasd af a ds d"));
    }

    public static int countWords(String s) {
        /*
          Please implement this method to
          return the word count in a given String.
          Assume that the parameter String can only contain spaces and alphanumeric characters.
         */
        
        Pattern pattern = Pattern.compile("[ ]{1,}");
        String str[] = pattern.split(s);
        return str.length;
    }
}
