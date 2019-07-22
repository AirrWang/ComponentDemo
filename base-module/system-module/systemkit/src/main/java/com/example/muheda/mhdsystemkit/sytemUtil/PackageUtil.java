package com.example.muheda.mhdsystemkit.sytemUtil;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by 13660 on 2018/11/15.
 */

public class PackageUtil {


    public static String getVersionName(Context mContext){
        return getCurrentVersion(mContext).versionName;
    }

    public static int getVersion(Context mContext){
        return getCurrentVersion(mContext).versionCode;
    }

    public static PackageInfo getCurrentVersion(Context mContext) {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = mContext.getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    mContext.getPackageName(), 0);
            return packInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAppInstall(String appPackage) {
        final PackageManager packageManager = SystemKit.mApplication.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(appPackage)) {
                    return true;
                }
            }
        }
        return false;
    }
}
