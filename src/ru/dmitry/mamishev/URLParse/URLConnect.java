/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.dmitry.mamishev.URLParse;

import ru.dmitry.mamishev.URLParse.URLTest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author dmitriy.mamishev
 */
class HtmlString {

    HtmlString(String str) {
        this.htmlString = str;
    }

    public GazInfo getInfoBill() {
        String html = this.htmlString;
        Document doc = Jsoup.parse(html);
        Elements ul = doc.getElementsByTag("ul");
        String numBill = "";
        numBill = ul.eq(1).text();
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
            billInfo = new GazInfo("", "", date, pay, "");
        }
        return billInfo;

    }
    final String htmlString;
    private static final Pattern SPLIT = Pattern.compile("[ ]{1,}");
}

public class URLConnect {

    public HtmlString getHtmlString(String path, String charSet) throws FileNotFoundException, IOException {
        HtmlString htmlString;
        if (path.toLowerCase().startsWith("http")) {
            htmlString = this.getStringFromURL(path, charSet);
        } else {
            htmlString = this.getStringFromFile(path, charSet);
        }
        return htmlString;
    }

    private HtmlString getStringFromFile(String path, String charSet) throws FileNotFoundException, IOException {
        String htmlString;
        try (FileInputStream input = new FileInputStream(path);
                InputStreamReader isr = new InputStreamReader(input, charSet);
                BufferedReader br = new BufferedReader(isr);) {
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            htmlString = sb.toString();
        }
        return new HtmlString(htmlString);
    }

    private HtmlString getStringFromURL(String sUrl, String charSet) {

        String sHTML = "Can not load page";
        URL url;
        InputStream is;
        BufferedReader buff = null;
        //URLConnection con;
        HttpURLConnection con;

        try {
            url = new URL(sUrl);
            con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("Cookie", "PHPSESSID=ecc618552b072f22c3494b8fefae2142");
            is = con.getInputStream();
            //is = url.openConnection().getInputStream();
            buff = new BufferedReader(new InputStreamReader(is, charSet));
            StringBuilder page = new StringBuilder();
            String tmp;
            while ((tmp = buff.readLine()) != null) {
                page.append(tmp).append("\n");
                //System.out.println(tmp);
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
        return new HtmlString(sHTML);

    }

    public static void main(String[] args) throws IOException {

        URLConnect c = new URLConnect();
        c.getHtmlString("c:\\!Temp\\q.html", "windows-1251").getInfoBill().print();
        c.getHtmlString("http://vrgaz.ru/lk/index.php?page=main", "windows-1251").getInfoBill().print();
    }

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
    private static Map<Integer, GazInfo> gazInfo = new TreeMap();
}
