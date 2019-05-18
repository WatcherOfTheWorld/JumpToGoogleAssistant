package xyz.codem.jumptogoogleassistant;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends android.app.Activity {
    String jumpPkg = "com.google.android.apps.googleassistant";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isApkInstalled(getApplicationContext(), jumpPkg)){
            // 打开 Package Manager 查找包
            PackageManager packageManager = getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(jumpPkg);
            // 跳转至目标App
            startActivity(intent);
        }else{
            // 跳转至商店下载目标app
            launchAppDetail(jumpPkg);
        }
        // 结束当前activity
        finish();
    }

    /**
     * isApkInstalled(Context context, String packageName)
     * 输入 package name, 检查当前设备是否已安装此package
     * @param context
     * @param packageName
     * @return Apk是否已安装
     */
    public static boolean isApkInstalled(Context context, String packageName) {
        try {
            // 报错就说明没有
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * launchAppDetail(String appPkg)
     * 跳转至默认应用商店安装包
     * @param appPkg
     */
    public void launchAppDetail(String appPkg){
        try {
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
