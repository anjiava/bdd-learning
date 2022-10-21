package org.mili;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.mili.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class AppMain {
    private final static Logger log = LoggerFactory.getLogger(AppMain.class);
    static Random rand = new Random();

    private static final String[] ln = {"es", "en", "pt"};
    private static final String[] area = {"BR", "MX"};
    private static final String[] brand = {"Sumsung", "Huawei", "HTC"};
    private static final String[] heightAndWidth = {"640*960", "640*1136", "750*1134", "1080*1920"};
    private static final String[] networkMode = {"3G", "4G", "WIFI"};
    private static final String[] failureCode = {"102", "201", "325", "433", "542", ""};

    public static void main(String[] args) {
        // 获取appkey
        String appkey = args.length > 0 ? args[0] : "gmall";
        // 循环次数，即生成的模拟数据的数量
        int loopLen = args.length > 1 ? Integer.parseInt(args[1]) : 10 * 100;
        // 生成的设备id的长度
        int midLen = args.length > 2 ? Integer.parseInt(args[2]) : 4;
        // 生成的用户id的长度
        int uidLen = args.length > 3 ? Integer.parseInt(args[3]) : 4;
        // 生成的商品id的长度
        int newsIdLen = args.length > 4 ? Integer.parseInt(args[4]) : 4;


        for (int i = 0; i < loopLen; i++) {
            JSONObject json = new JSONObject();
            json.put("ap", appkey);
            json.put("cm", generateCommonFields(midLen, uidLen));
            JSONArray eventsArray = new JSONArray();
            int f = rand.nextInt(2);
            if (f == 0) {
                if (rand.nextBoolean()) {
                    eventsArray.add(generateStartLog());
                }
            } else {
                if (rand.nextBoolean()) {
                    eventsArray.add(generateDisplay(newsIdLen));
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateNewsDetail(newsIdLen));
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateLoading());
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateAd(newsIdLen));
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateNotification(newsIdLen));
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateForeground());
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateBackground());
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateError());
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateComment());
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generateFavorites());
                }
                if (rand.nextBoolean()) {
                    eventsArray.add(generatePraise());
                }
            }
            json.put("et", eventsArray);
            log.info(System.currentTimeMillis() + "|" + json.toJSONString());
        }
    }

    private static JSONObject generatePraise() {
        EventLogPraise praise = new EventLogPraise();
        praise.setId(rand.nextInt(10));
        praise.setUserId(rand.nextInt(10));
        praise.setTargetId(rand.nextInt(10));
        praise.setType(rand.nextInt(4)+1);
        praise.setAddTime(String.valueOf(System.currentTimeMillis() - rand.nextInt(99999999)));
        return packEventJson("praise", praise);
    }

    private static JSONObject generateFavorites() {
        EventLogFavorites favorites = new EventLogFavorites();
        favorites.setId(rand.nextInt(10));
        favorites.setUserId(rand.nextInt(10));
        favorites.setCourseId(rand.nextInt(10));
        favorites.setAddTime(String.valueOf(System.currentTimeMillis() - rand.nextInt(99999999)));
        return packEventJson("favorites", favorites);
    }

    private static JSONObject generateComment() {
        EventLogComment comment = new EventLogComment();
        comment.setCommentId(rand.nextInt(10));
        comment.setUserId(rand.nextInt(10));
        comment.setpCommentId(rand.nextInt(5));
        comment.setOtherId(rand.nextInt(10));
        comment.setPraiseCount(rand.nextInt(1000));
        comment.setReplyCount(rand.nextInt(200));
        comment.setContent(getContent());
        comment.setAddTime(String.valueOf(System.currentTimeMillis() - rand.nextInt(99999999)));
        return packEventJson("comment", comment);
    }

    private static JSONObject generateError() {
        AppErrorLog errorLog = new AppErrorLog();
        //错误摘要
        String[] errorBriefs = {"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)", "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)"};
        //错误详情
        String[] errorDetails = {"java.lang.NullPointerException\\n    " + "at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\n " + "at cn.lift.dfdf.web.AbstractBaseController.validInbound", "at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " + "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" + " at java.lang.reflect.Method.invoke(Method.java:606)\\n"};

        //错误摘要
        errorLog.setErrorBrief(errorBriefs[rand.nextInt(errorBriefs.length)]);
        //错误详情
        errorLog.setErrorDetail(errorDetails[rand.nextInt(errorDetails.length)]);
        return packEventJson("error", errorLog);
    }

    private static JSONObject generateBackground() {
        EventLogActiveBackground background = new EventLogActiveBackground();
        background.setActiveSource(String.valueOf(rand.nextInt(3) + 1));
        return packEventJson("active_background", background);
    }

    private static JSONObject generateForeground() {
        EventLogActiveForeground foreground = new EventLogActiveForeground();
        foreground.setAccess(rand.nextInt(2) == 1 ? "1" : "");
        foreground.setPushId(String.valueOf(rand.nextInt(3) + 1));
        return packEventJson("active_foreground", foreground);

    }

    private static JSONObject generateNotification(int newsIdLen) {
        EventLogNotification notification = new EventLogNotification();
        notification.setAction(String.valueOf(rand.nextInt(4) + 1));
        notification.setType(String.valueOf(rand.nextInt(4) + 1));
        notification.setApTime(String.valueOf(System.currentTimeMillis() - rand.nextInt(99999999)));
        notification.setContent("");
        return packEventJson("notification", notification);
    }

    private static JSONObject generateAd(int newsIdLen) {
        EventLogAd ad = new EventLogAd();
        ad.setEntry(String.valueOf(rand.nextInt(3) + 1));
        ad.setAction(String.valueOf(rand.nextInt(5) + 1));
        ad.setContent(String.valueOf(rand.nextInt(10) > 6 ? 2 : 1));
        ad.setDetail(failureCode[rand.nextInt(6)]);
        ad.setSource(String.valueOf(rand.nextInt(4) + 1));
        ad.setNewsType(String.valueOf(rand.nextInt(10)));
        ad.setBehavior(String.valueOf(rand.nextInt(2) + 1));
        ad.setShowStyle(String.valueOf(rand.nextInt(6)));
        return packEventJson("ad", ad);
    }

    private static JSONObject generateLoading() {
        EventLogLoading loading = new EventLogLoading();
        loading.setAction(String.valueOf(rand.nextInt(3) + 1));
        loading.setLoadingTime(String.valueOf(rand.nextInt(70)));
        ;
        loading.setType1(failureCode[rand.nextInt(6)]);
        loading.setLoadingWay(String.valueOf(rand.nextInt(2) + 1));
        loading.setType((String.valueOf(rand.nextInt(3) + 1)));
        loading.setExtend1("");
        loading.setExtend2("");
        return packEventJson("loading", loading);
    }

    private static JSONObject generateNewsDetail(int newsIdLen) {
        EventLogNewsDetail newsDetail = new EventLogNewsDetail();
        newsDetail.setEntry(String.valueOf(rand.nextInt(3) + 1));
        newsDetail.setAction(String.valueOf(rand.nextInt(4) + 1));
        newsDetail.setNewsId('n' + getRandomDigits(newsIdLen));
        newsDetail.setShowStyle(String.valueOf(rand.nextInt(6)));
        newsDetail.setNewsStayTime(String.valueOf(rand.nextInt(70)));
        newsDetail.setLoadingTime(String.valueOf(rand.nextInt(70)));
        newsDetail.setType1(failureCode[rand.nextInt(6)]);
        newsDetail.setCategory(String.valueOf(rand.nextInt(100) + 1));
        return packEventJson("newsdetail", newsDetail);
    }


    private static JSONObject generateDisplay(int newsIdLen) {
        EventLogDisplay display = new EventLogDisplay();
        display.setAction(String.valueOf(rand.nextInt(10) > 7 ? 2 : 1));
        display.setNewsid('n' + getRandomDigits(newsIdLen));
        display.setPlace(String.valueOf(rand.nextInt(6)));
        display.setExtend1(String.valueOf(rand.nextInt(2) + 1));
        display.setCategory(String.valueOf(rand.nextInt(100) + 1));
        return packEventJson("display", display);
    }

    private static JSONObject generateStartLog() {
        AppStartLog startLog = new AppStartLog();
        startLog.setEntry(String.valueOf(rand.nextInt(5) + 1));
        startLog.setOpenAdType(String.valueOf(rand.nextInt(2) + 1));
        startLog.setAction(String.valueOf(rand.nextInt(10) > 8 ? 2 : 1));
        startLog.setLoadingTime(String.valueOf(rand.nextInt(20)));
        startLog.setDetail(failureCode[rand.nextInt(6)]);
        return packEventJson("start", startLog);
    }

    private static JSONObject generateCommonFields(int midLen, int uidLen) {
        AppBase appBase = new AppBase();
        appBase.setMid('m' + getRandomDigits(midLen));
        appBase.setUid('u' + getRandomDigits(uidLen));
        appBase.setVc(String.valueOf(rand.nextInt(20)));
        appBase.setVn("1." + rand.nextInt(4) + "." + rand.nextInt(10));
        appBase.setOs("8." + rand.nextInt(3) + "." + rand.nextInt(10));
        appBase.setL(ln[rand.nextInt(3)]);
        appBase.setSr(getRandomChar(1));
        appBase.setAr(area[rand.nextInt(2)]);
        // 手机品牌
        appBase.setBa(brand[rand.nextInt(3)]);
        // 手机型号
        appBase.setMd(appBase.getBa() + "-" + rand.nextInt(20));
        // sdk版本
        appBase.setSv("V2." + rand.nextInt(10) + "." + rand.nextInt(10));
        appBase.setG(getRandomCharAndNum(8) + "@gmail.com");
        appBase.setHw(heightAndWidth[rand.nextInt(4)]);
        appBase.setT(String.valueOf(System.currentTimeMillis() - rand.nextInt(99999999)));
        appBase.setNw(networkMode[rand.nextInt(3)]);

        //      拉丁美洲 西经34°46′至西经117°09；北纬32°42′至南纬53°54′
        //      经度
        appBase.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "");
        //      纬度
        appBase.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "");
        return (JSONObject) JSON.toJSON(appBase);
    }


    private static String getRandomDigits(int len) {

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            result.append(rand.nextInt(10));
        }

        return result.toString();
    }

    public static String getRandomChar(Integer length) {

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            // 大写字符
            str.append((char) (65 + random.nextInt(26)));
        }
        return str.toString();
    }

    public static String getRandomCharAndNum(Integer length) {

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            if (random.nextBoolean()) {
                // 字符串, 取得大写字母
                str.append((char) (65 + random.nextInt(26)));
            } else { // 数字
                str.append(random.nextInt(10));
            }
        }
        return str.toString();
    }

    static JSONObject packEventJson(String eventName, Object obj) {

        JSONObject eventJson = new JSONObject();

        eventJson.put("ett", (System.currentTimeMillis() - rand.nextInt(99999999)) + "");
        eventJson.put("en", eventName);
        eventJson.put("kv", JSON.toJSON(obj));

        return eventJson;
    }

    static public String getContent() {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < rand.nextInt(100); i++) {
            str.append(getRandomChar());
        }

        return str.toString();
    }

    private static char getRandomChar() {

        String str = "";
        int hightPos; //
        int lowPos;

        Random random = new Random();

        //随机生成汉子的两个字节
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);
    }

}
