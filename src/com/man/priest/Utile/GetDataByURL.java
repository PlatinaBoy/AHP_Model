package com.man.priest.problem.Utile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * ClassName: GetDataByURL <br/>
 * Description: <br/>
 *
 * @author PlatinaBoy<br />
 */
public class GetDataByURL {
    public static String getDataByUrl(String args) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        String urldd = "";
        try {
            //拼接参数，转义参数
            String connUrl = urldd + args;

            //创建连接
            URL url1 = new URL(connUrl);
            conn = (HttpURLConnection) url1.openConnection();
            conn.setUseCaches(false);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            //获取并解析数据
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }


}
