/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitriy.mamishev
 */
import java.util.*;

public class T3 {

    public static Set<Object> getIntersection(Set<Object> a, Set<Object> b) {
        /*
          Please implement this method to
          return a Set equal to the intersection of the parameter Sets
          The method should not chage the content of the parameters.
         */
        Set res = new HashSet();
        res.addAll(a);
        res.retainAll(b);

        return res;
    }

    public static Set<Object> getUniqueElements(Set<Object> a, Set<Object> b) {
        /*
          Please implement this method to
          return a set of elements that can be found only in set a or set b,
          but not in both Sets.
          The method should not change the content of the parameters.
         */
        Set res = new HashSet();
        Set res1 = new HashSet();
        res.addAll(b);
        res.retainAll(a);
        res1.addAll(a);
        res1.addAll(b);
        res1.removeAll(res);

        //res.removeAll(a);
        return res1;
    }

    public static void main(String[] args) {
        Set a = new HashSet();
        a.add("str1");
        a.add("str2");
        a.add("str3");
        Set b = new HashSet();
        b.add("str1");
        b.add("str5");
        b.add("str7");
        //T3.getIntersection(a, b);
        System.out.println(T3.getUniqueElements(a, b));
    }
}
