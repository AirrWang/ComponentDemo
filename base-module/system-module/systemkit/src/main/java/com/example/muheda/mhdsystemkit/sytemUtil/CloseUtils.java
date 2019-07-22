package com.example.muheda.mhdsystemkit.sytemUtil;

import java.io.Closeable;

public final class CloseUtils {

    private  CloseUtils(){

    }

    /**
     * 关闭Closeable对象
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if (null != closeable){
            try{
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
