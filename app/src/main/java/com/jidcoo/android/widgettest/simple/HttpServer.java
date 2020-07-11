package com.jidcoo.android.widgettest.simple;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static final String IP = "http://192.168.1.9:8080/CommentService";
    public static ExecutorService threadPool = Executors.newSingleThreadExecutor();

    public static void getData(final String url, final Handler handler, final int code) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String data = get(url);
                Message msg = Message.obtain();
                msg.what = code;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        });
    }

    public static String get(String urlStr) {
        //get的方式提交就是url拼接的方式
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            //获得结果码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                return inputStream2String(is);
            } else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }


    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];

        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        in.close();
        return out.toString();
    }
}
