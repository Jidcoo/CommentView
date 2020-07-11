package com.jidcoo.android.widgettest.simple;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;

import static com.jidcoo.android.widgettest.simple.HttpServer.threadPool;

/**
 * 注意这里读取文件是在主线程的。
 */
public class LocalServer {
    private Context context;
    private String api;
    private volatile boolean isFirstLoad = true;

    public LocalServer(Context context, String api) {
        this.context = context;
        this.api = api;
    }

    public String get(int code, Handler handler, int handlerId) {
        switch (code) {
            case 1:
                //首次加载和刷新
                return readFile(api + "/p1.json", handler, handlerId);
            case 2:
                //加载第二页
                return readFile(api + "/p2.json", handler, handlerId);
            case 3:
                //加载第三页
                return readFile(api + "/p3.json", handler, handlerId);
            case 4:
                //加载第一页的更多回复
                return readFile(api + "/p1_r1.json", handler, handlerId);
            case 5:
                //加载第三页的更多回复（第二页）
                return readFile(api + "/p3_r1.json", handler, handlerId);
            case 6:
                //加载第三页的更多回复（第二页）
                return readFile(api + "/p3_r2.json", handler, handlerId);
        }
        return null;
    }


    private String readFile(final String path, final Handler handler, final int handlerId) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                try {
                    is = context.getAssets().open(path);
                    String rs = HttpServer.inputStream2String(is);
                    if (!isFirstLoad) {
                        Thread.sleep(1500);//模拟网络延迟
                    } else {
                        isFirstLoad = false;
                    }
                    Message message = Message.obtain();
                    message.what = handlerId;
                    message.obj = rs;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        return "";
    }
}
