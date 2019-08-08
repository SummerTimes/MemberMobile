package com.kk.app.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;

import java.io.File;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

/**
 * 获取apk相关信息
 */
public class ApkUtil {

    /***
     * 获取当前App的VersionCode
     * @return versionCode   0获取失败
     */
    public static int getCurrentVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /***
     * 获取当前App的VersionName
     * @param context 当前上下文
     * @return
     */
    public static String getCurrentVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 获取APK的icon
     * @param context 当前上下文
     * @param apkPath apk在SD中的路径
     * @return
     */
    public static Drawable getApkIcon(Context context, String apkPath) {

        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            appInfo.sourceDir = apkPath;
            appInfo.publicSourceDir = apkPath;
            return appInfo.loadIcon(pm);
        }
        return null;
    }

    /**
     * 获取已安装app签名
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String getInstalledApkSignature(Context context,
                                                  String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> apps = pm
                .getInstalledPackages(PackageManager.GET_SIGNATURES);

        Iterator<PackageInfo> iter = apps.iterator();
        while (iter.hasNext()) {
            PackageInfo packageinfo = iter.next();
            String thisName = packageinfo.packageName;
            if (thisName.equals(packageName)) {
                return packageinfo.signatures[0].toCharsString();
            }
        }

        return null;
    }

    /***
     * 获取未安装包的签名
     * @param context 当前上下文
     * @param apkPath 全路径+包名
     * @return
     */
    public static String getUnInstalledApkSignature(Context context,
                                                    String apkPath) {
        String sign = null;

        Class<?> clazz;
        try {
            clazz = Class.forName("android.content.pm.PackageParser");
            Object packageParser = (Build.VERSION.SDK_INT >= 21 ? clazz
                    .getConstructor().newInstance() : clazz.getConstructor(
                    String.class).newInstance(""));

            Object packag = null;
            if (Build.VERSION.SDK_INT >= 21) {
                Method method = clazz.getMethod("parsePackage", File.class,
                        int.class);
                packag = method
                        .invoke(packageParser, new File(apkPath), 0x0004);
            } else {
                Method method = clazz.getMethod("parsePackage", File.class,
                        String.class, DisplayMetrics.class, int.class);
                packag = method.invoke(packageParser, new File(apkPath), null,
                        context.getResources().getDisplayMetrics(), 0x0004);
            }
            Method collectCertificatesMethod = clazz.getMethod(
                    "collectCertificates",
                    Class.forName("android.content.pm.PackageParser$Package"),
                    int.class);
            collectCertificatesMethod.invoke(packageParser, packag,
                    PackageManager.GET_SIGNATURES);
            Signature mSignatures[] = (Signature[]) packag.getClass()
                    .getField("mSignatures").get(packag);

            Signature apkSignature = mSignatures.length > 0 ? mSignatures[0]
                    : null;

            if (apkSignature != null) {
                sign = apkSignature.toCharsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sign;
    }

    /**
     * 获取已安装的应用的签名信息的MD5和SHA字符串
     *
     * @param context
     * @param packageName
     * @return
     */
    public static String[] getInstalledApkMD5SHA(Context context,
                                                 String packageName) {
        // 存储MD5串和SHA串
        String[] strArr = new String[2];

        try {
            PackageManager pm = context.getPackageManager();
            // 获取签名信息
            @SuppressLint("PackageManagerGetSignatures") PackageInfo packageInfo = pm.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sign.toByteArray());

            byte[] md5Digest = md.digest();
            // MD5串
            String md5DigestStr = StringUtil.bytesToHexString(md5Digest);

            MessageDigest md2 = MessageDigest.getInstance("SHA1");
            md2.update(sign.toByteArray());
            byte[] shaDigest = md2.digest();
            // SHA串
            String shaDigestStr = StringUtil.bytesToHexString(shaDigest);

            strArr[0] = md5DigestStr;
            strArr[1] = shaDigestStr;

            return strArr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strArr;
    }
}
