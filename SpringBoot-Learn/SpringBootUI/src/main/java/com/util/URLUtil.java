package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtil {
    public static String getURLContent(String urlPath) throws IOException {
        URL url= new URL(urlPath);
        //首先打开HttpUrl连接
        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
        urlConnection.connect();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String json;
        StringBuffer buffer=new StringBuffer();
        //把接受到的数据
        while ((json=bufferedReader.readLine())!=null){
            buffer.append(json);
        }
        bufferedReader.close();
        urlConnection.disconnect();//关闭连接
        return buffer.toString();
    }
}
