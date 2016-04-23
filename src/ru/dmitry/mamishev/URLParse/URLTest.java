/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmitry.mamishev.URLParse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import jxl.Workbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import jxl.write.Number;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author dmitriy.mamishev
 */
public class URLTest {

    private static String getStringFromURL(String sUrl, String charSet) {

        String sHTML = "Can not load page";
        URL url;
        InputStream is;
        BufferedReader buff = null;
        //URLConnection con;
        HttpURLConnection con;

        try {
            url = new URL(sUrl);
            con = (HttpURLConnection) url.openConnection();
            
            con.setRequestMethod("POST");
            con.setRequestProperty("login", "mail");
            con.setRequestProperty("pass", "test");

            con.addRequestProperty("Cookie", "PHPSESSID=ecc618552b072f22c3494b8fefae2142");
            is = con.getInputStream();
            //is = url.openConnection().getInputStream();
            buff = new BufferedReader(new InputStreamReader(is, charSet));
            StringBuilder page = new StringBuilder();
            String tmp;
            while ((tmp = buff.readLine()) != null) {
                page.append(tmp).append("\n");
                System.out.println(tmp);
            }
            System.out.println();
            sHTML = page.toString();

        } catch (MalformedURLException ex) {
            Logger.getLogger(URLTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(URLTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (buff != null) {
                    buff.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(URLTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sHTML;
    }

    public static Map<String, Integer> countWords(String str) {
        if (str != null) {
            Map<String, Integer> words = new HashMap<>();
            Pattern pattern = Pattern.compile("[\" =/.<	>?   ]");
            for (String s : pattern.split(str)) {
                if (words.containsKey(s)) {
                    words.put(s, words.get(s) + 1);
                } else {
                    words.put(s, 1);
                }
            }
            return words;
        }
        return null;
    }

    public static void printMap(Map<String, Integer> map) {
        map.entrySet().stream().forEach((set) -> {
            System.out.printf("key is \"%s\" value = \"%d\"\n", set.getKey(), set.getValue());
        });
    }

    private static String getStringFromFile(String path, String charSet) throws FileNotFoundException, IOException {
        String res;
        try (FileInputStream input = new FileInputStream(path);
                InputStreamReader isr = new InputStreamReader(input, charSet);
                BufferedReader br = new BufferedReader(isr);) {
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            res = sb.toString();
        }
        return res;
    }

    /**
     * get info from file or url
     *
     */
    public static GazInfo getInfoBill(String path, String charSet) throws IOException {
        String html;
        if (path.toLowerCase().startsWith("http")) {
            html = URLTest.getStringFromURL(path, charSet);
        } else {
            html = URLTest.getStringFromFile(path, charSet);
        }
        Document doc = Jsoup.parse(html);
        Elements ul = doc.getElementsByTag("ul");
        String numBill = "";
        numBill = ul.eq(1).text();

//        if (!ul.subList(1, 1).isEmpty()) {
//            numBill = ul.get(1).text();
//        }
        Document bElements = Jsoup.parseBodyFragment(ul.toString());
        Elements b = bElements.getElementsByTag("b");
        GazInfo billInfo = null;

        String date = "";
        String pay = "";
        if (b.size() > 2) {
            String[] ss = SPLIT.split(b.get(2).text());
            if (ss.length > 0) {
                date = ss[0];
                pay = ss[1];
            }
            billInfo = new GazInfo(b.get(0).text(), b.get(1).text(), date, pay, numBill);
        } else {
            billInfo = new GazInfo("", "", date, pay, numBill);
        }
        //billInfo = new GazInfo(b.get(0).text(), b.get(1).text(), date, pay);

        return billInfo;

    }

    public static Boolean doFile() {

        try {
            WritableWorkbook workbook; // переменная рабочей книги
            WritableSheet sheet;
            WritableCellFormat arial12BoldFormat;
            Label fio;
            Label adress;
            Label date;
            Number lastSum;
            WritableFont arial8ptBold
                    = new WritableFont(WritableFont.ARIAL, 8, WritableFont.NO_BOLD);
            arial12BoldFormat = new WritableCellFormat(arial8ptBold);
            // arial12BoldFormat.setWrap(true);
            workbook = Workbook.createWorkbook(new File("c:\\Users\\dmitriy.mamishev\\Documents\\unload.xls"));
            sheet = workbook.createSheet("Create from URLTest", 0);

            for (Map.Entry<Integer, GazInfo> line : gazInfo.entrySet()) {
                fio = new Label(0, line.getKey(), gazInfo.get(line.getKey()).getFio(), arial12BoldFormat);
                adress = new Label(1, line.getKey(), gazInfo.get(line.getKey()).getAdress(), arial12BoldFormat);
                date = new Label(2, line.getKey(), gazInfo.get(line.getKey()).getDate(), arial12BoldFormat);
                lastSum = new Number(3, line.getKey(), Integer.valueOf(gazInfo.get(line.getKey()).getLastSum()));

                sheet.addCell(fio);
                sheet.addCell(adress);
                sheet.addCell(date);
                sheet.addCell(lastSum);

            }
            workbook.write();
            workbook.close();
            return true;

        } catch (WriteException | IOException ex) {
            Logger.getLogger(URLTest.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    public static void mapToFile() throws IOException {
        String url = "http://vrgaz.ru/lk/index.php?page=ls&lss=555";
        String resStr;
        // for (int i = 619; i < 620; i++) {
        resStr = url + Integer.toString(1);
        System.out.println(URLTest.getInfoBill(resStr, "windows-1251"));
        gazInfo.put(1, URLTest.getInfoBill(resStr, "windows-1251"));
        //}
        System.out.println(URLTest.doFile());
    }

    public static void main(String[] args) throws IOException {
//        String resPage;
//        resPage = URLTest.getStringFromURL("http://vrgaz.ru/lk/index.php?page=ls&lss=555", "windows-1251");
//        System.out.print(resPage);
//        URLTest.printMap(URLTest.countWords(resPage));
//
        String resStr;
        for (int i = 555, j = 0; i < 556; i++, j++) {
            resStr = "c:\\!Temp\\q.html";
            //  resStr = "http://vrgaz.ru/lk/index.php?page=ls&lss=555";
            System.out.println(URLTest.getInfoBill(resStr, "windows-1251"));
            //gazInfo.put(j, URLTest.getInfoBill(resStr, "windows-1251"));
        }
        //System.out.println(URLTest.doFile());
    URLTest.getStringFromURL("http://vrgaz.ru/lk/index.php?page=ls&lss=555", "windows-1251");

    }
    private static final Pattern SPLIT = Pattern.compile("[ ]{1,}");
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private static Map<Integer, GazInfo> gazInfo = new TreeMap();
}
