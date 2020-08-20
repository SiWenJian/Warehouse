package com.pmcc.warehouse.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * @author zs
 *         Created by 天地 on 2017/5/9.
 *         简单工具类
 */

public class StringUtils {
    //public static final String REGEX_HANZI = "([\\u4E00-\\u9FBF]{1,10}·?[\\u4E00-\\u9FBF]{1,10}){1,5}";
    public static final String REGEX_ID_CARD_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String REGEX_ID_CARD_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    public static final String REGEX_TELREGEX = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
    public static final String REGEX_HANZI = "([\\u4e00-\\u9fa5]{2,15})";
    public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    static String phoneFormat = "[1][35678]\\d{9}";
    static String pwdFormat = "^[a-zA-Z0-9]{6,}$";//包涵文字数字
    static String TelFormat = "^((0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";//固定电话格式
    static String cardFormat = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";

    /**
     * 验证固话号码
     *
     * @param telephone
     * @return
     */
    public static boolean isTelephone(String telephone) {
        //String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return telephone.matches(TelFormat);
    }

    /**
     * 验证手机号
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneFormat(String phone) {
        return phone.matches(phoneFormat);
    }

    /**
     * 验证密码
     *
     * @param pwd
     * @return
     */
    public static boolean isPwdFormat(String pwd) {
        return pwd.matches(pwdFormat);
    }

    /**
     * 验证身份证号
     *
     * @param card
     * @return
     */
    public static boolean isCardFormat(String card) {
        return card.matches(cardFormat);
    }

    /**
     * 验证身份证号
     *
     * @param card
     * @return
     */
    public static boolean isEmailFormat(String card) {
        return card.matches(REGEX_EMAIL);
    }

    /**
     * 获得当前版本号
     *
     * @return
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            versionCode = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获得当前版本名称
     *
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            // 获取软件版本号，对应AndroidManifest.xml下的versionCode
            versionName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 字段是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得手机的唯一表示
     *
     * @param context
     * @return
     */
    public static String getdeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission")
        String deviceId = telephonyManager.getDeviceId();
        return deviceId;
    }

    /**
     * 得到日期格式
     *
     * @return
     */
    public static String getTimeFormat() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 得到日期格式
     *
     * @return
     */
    public static String getTimeFormat1() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 得到压缩图片  分辨率压缩
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        LogUtils.i("文件路径"+ "getSmallBitmap: " + filePath);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 240, 400);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 压缩图片的计算方式  计算原图片大小
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                             int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 质量压缩
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {
        if (image == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 1500) {  //循环判断压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 得到临时图片路径
     *
     * @return
     * @throws IOException
     */
    public static File bitmapToFile(Bitmap mBitmap, File cacheLocation, String fileName) {
        File filePic = null;
        try {
            filePic = new File(cacheLocation, fileName);
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePic;
    }

    /**
     * 提升读写权限，否则会出现解析异常的情况
     *
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static void setPermission(String filePath) {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭键盘
     *
     * @param context
     */
    public static void closeKeyboard(Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 打开键盘键盘
     *
     * @param context
     */
    public static void showKeyboard(Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    /**
     * 隐藏部分手机号
     *
     * @param num
     * @return
     */
    public static String hidePhone(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (i >= 3 && i <= 6) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    /**
     * 隐藏部分身份证信息
     *
     * @param num
     * @return
     */
    public static String hideCard(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if (6 < i && i < num.length() - 6) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();

    }

    /**
     * 得到小数点后，两位
     *
     * @param num
     * @return
     */
    public static String get2XS(double num) {
        return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 返回100，000.000元有千位符和后三位小数
     *
     * @param amount
     * @return
     */
    public static String getSeperateWithFloatAmount(double amount) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(amount);
    }

    /**
     * @param amount
     * @return
     */
    public static String getDoublePoint4(double amount) {
        DecimalFormat df = new DecimalFormat("0.0000");
        return df.format(amount);
    }

    /**
     * 将整型添加“,”隔位符 “123456789”-》“123，456，789”
     *
     * @param number
     * @return
     */
    public static String addSeparateSign(int number) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }

    /*
    * 检查手机上是否安装了指定的软件
   * @param context
   * @param packageName：应用包名
   * @return
   */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }
}
