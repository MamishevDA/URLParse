/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FainClasses;

/**
 *
 * @author dmitriy.mamishev
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class HttpBasicAuth {

    public static void auth() {

        String sHTML = "Can not load page";
        URL url;
        InputStream is;
        BufferedReader buff = null;

        try {
            url = new URL("http://vrgaz.ru/lk/index.php?page=ls&lss=1600013620");
            url.getAuthority();

            String encoding = Base64.encodeBase64String("md_dimka@mail.ru:ufpyflt;ls".getBytes());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Cookie","PHPSESSID=343266771f5de3a489ba82fe79809854");
           // connection.
            //connection.setRequestProperty("Authorization", "Basic " + encoding);

            /*connection.setRequestProperty("avl","md_dimka@mail.ru");
            connection.setRequestProperty("avp","ufpyflt;ls");
             */
            //connection.setRequestProperty("page", "ls");
            //connection.setRequestProperty("lss", "1600013620");

            //URL url = new URL("http://vrgaz.ru/lk/");  
            // url = new URL(sUrl);
            //is = url.openStream();
            is = connection.getInputStream();
            buff = new BufferedReader(new InputStreamReader(is, "windows-1251"));
            StringBuilder page = new StringBuilder();
            String tmp;
            while ((tmp = buff.readLine()) != null) {
                page.append(tmp).append("\n");
            }

            sHTML = page.toString();
            System.out.println(sHTML);
//            InputStream content = (InputStream) connection.getInputStream();
//            BufferedReader in
//                    = new BufferedReader(new InputStreamReader(content));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HttpBasicAuth.auth();

    }

}
