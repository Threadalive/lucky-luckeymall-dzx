package com.lucky.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
/**
 * @Description 用于短信发送
 *
 * @Author zhenxing.dong
 * @Date 2019/8/22 20:00
 */
public class HttpSender {

    /**
     * 数组长度
     */
    private static final int ARRAY_LENGTH = 10;

    /**
     * 短信发送的主要方法
     * @param url 应用地址，类似于http://api2.santo.cc/submit
     * @param command 命令
     * @param cpid 账号
     * @param cppwd 密码
     * @param da 手机号码
     * @param sm 短信内容
     * @param sa 发送者手机号
     * @return 结果
     * @throws Exception 抛异常
     */
    public static String sendPost(String url, String command, String cpid, String cppwd, String da, String sm,
                                  String sa) throws Exception {
        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager());
        PostMethod method = new PostMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "", false));
            method.setRequestBody(new NameValuePair[] {
                    new NameValuePair("command", command),
                    new NameValuePair("cpid", cpid),
                    new NameValuePair("cppwd", cppwd),
                    new NameValuePair("da", da),
                    new NameValuePair("sm", sm),
                    new NameValuePair("sa", sa) });
            HttpMethodParams params = new HttpMethodParams();
            params.setContentCharset("UTF-8");
            method.setParams(params);
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[ARRAY_LENGTH];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                in.close();// 1
                baos.close();// 2
                return URLDecoder.decode(baos.toString(), "UTF-8");
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } finally {
            method.releaseConnection();
        }
    }
}
