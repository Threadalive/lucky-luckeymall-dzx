package com.lucky.util;

/**
 * @Description 短信发送调用者
 *
 * @Author zhenxing.dong
 * @Date 2019/8/22 20:06
 */
public class HttpSenderMain {

    /**
     * 发送短信的账号(非登录账号)
     */
    private static String cpid = "ydatga";

    /**
     * 发送短信的密码(非登录密码)
     */
    private static String cppwd = "JGOhK4QX";

    /**
     * 种子
     */
    private static final int BEED = 10;

    /**
     * 调用方法
     * @param phoneNum 手机号码
     * @return 验证码
     */
    public static String sendMessage(String phoneNum) {
        char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String num = "";
        for (int index = 0; index < 6; index++) {
            num += nums[(int) (Math.random() * BEED)];
        }
        String url = "http://api2.santo.cc/submit";  //应用地址 (无特殊情况时无需修改)
        String command = "MT_REQUEST";        // MT_REQUEST：短信  VO_REQUEST：语音
        String da = "86"+phoneNum; //目标号码 如中国大陆：8613800000000(支持多号码批量提交，多个号码之间用半角逗号分隔，最多100个)
        String sm = "您本次的注册验证码为" + num + "，" + "请不要泄露给他人，该验证码5分钟内有效。"; //短信内容(URL utf-8编码)
        String sa = "10000";        //自定义发送者号码 (仅限数字或者字母,纯数字支持最大16个字符,带有字母支持最大11个字符)
        try {
            HttpSender.sendPost(url, command, cpid, cppwd, da, sm, sa);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

}
