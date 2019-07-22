package com.example.amapservice;

import android.content.Context;
import android.content.Intent;

import com.amap.api.location.AMapLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfei on 2019/4/10.
 */
public class LocationUtils {
    private static List<onLocationListener> listeners = new ArrayList<>();

    public static void start(Context context, onLocationListener myOnLocationListener) {
        context.startService(new Intent(context, AMapService.class));
        if (!listeners.contains(myOnLocationListener)) {
            listeners.add(myOnLocationListener);
        }
    }

    public static List<onLocationListener> getOnLocationListener() {
        return listeners;
    }

    public  static void removeLocationListener(onLocationListener myOnLocationListener){
        listeners.remove(myOnLocationListener);
    }


    public interface onLocationListener {
        void getData(AMapLocation aMapLocation);
    }
}
