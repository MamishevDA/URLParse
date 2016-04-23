/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FainClasses;

import ru.dmitry.mamishev.URLParse.URLTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author dmitriy.mamishev
 */
public class SteamAPITest {

    public static <K, V extends Comparable<? super V>> Map<K, V>
            sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    public static void printMap(Map<String, Integer> map) {
        map.entrySet().stream().forEach((set) -> {
            //   mm.entrySet().stream().forEach((set) -> {
            System.out.printf("key is \"%s\" value = \"%d\"\n", set.getKey(), set.getValue());
        });
    }

    public static void testCity() {
        String[] city = {"voronezh", "moscow", "odessa", "omsk"};
        ArrayList<String> s = new ArrayList<>(Arrays.asList(city));
        s.stream().map(strr -> strr + "_1").map(String::toUpperCase).filter(length2 -> length2.length() > 4).forEach(ss -> System.out.println(ss));

    }

    public static void main(String[] args) {

        Map<String, Integer> empl = new HashMap<>();
        empl.put("programmer", 1000);
        empl.put("chef", 1500);
        empl.put("boss", 2000);
        /*
        x.entrySet().stream()
        .collect(Collectors.toMap(
            e -> e.getKey(),
            e -> Integer.parseInt(e.getValue())
        ));*/
        Map<String, Integer> emplNew = empl.entrySet().stream().filter(p -> p.getValue() > 1000).collect(Collectors.toMap(p1 -> p1.getKey(), p1 -> p1.getValue()));
        //.forEach(p2->System.out.printf("key is %s; value = %d\n",p2.getKey(),p2.getValue()));
        URLTest.printMap(emplNew);
    }

}
