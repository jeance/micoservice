package com.te.micoservice.common.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hcw07605 on 2017/3/1.
 */
public class HttpUtils {

    public enum HttpEnum {
        VeryGood("请求成功", 0),
        Timeout("请求超时", 1),
        NetError("网络错误", 2);

        private String dec;

        private int index;

        private HttpEnum(String dec, int index) {
            this.dec = dec;
            this.index = index;
        }

        public String getDec() {
            return dec;
        }

        public int getIndex() {
            return index;
        }
    }

    private String requestUrl;

    private String sentData;


    private String method = "post";

    private String contentType = "application/x-www-form-urlencoded";

    private boolean keepAlive = true;

    private int timeout = 5000;

    private String charset = "UTF-8";

    public HttpUtils(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getSentData() {
        return sentData;
    }

    public void setSentData(String sentData) {
        this.sentData = sentData;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }


    /**
     * 一般post和get请求
     *
     * @return
     */
    public Map<HttpEnum, String> sendRequest() {
        Map<HttpEnum, String> mapResult = new HashMap<HttpEnum, String>();
        OutputStreamWriter osw = null;
        BufferedReader in = null;
        StringBuffer line = new StringBuffer();
        try {
            if ("get".equals(method.toLowerCase())) {
                requestUrl = requestUrl + "?" + sentData;
            }
            URL realUrl = new URL(requestUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            if (keepAlive) {
                conn.setRequestProperty("connection", "Keep-Alive");
            }
            conn.setRequestProperty("Charset", charset);
            conn.setRequestProperty("Content-Type", contentType);
            conn.setConnectTimeout(timeout);
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            if ("post".equals(method.toLowerCase())) {
                // 发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                osw = new OutputStreamWriter(conn.getOutputStream(), charset);
                // 发送请求参数
                osw.write(sentData.substring(0, sentData.length()));
                // flush输出流的缓冲
                osw.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charset));
            int ch;
            while ((ch = in.read()) > -1) {
                line.append((char) ch);
            }
            mapResult.put(HttpEnum.VeryGood, line.toString());

        } catch (Exception e) {
            mapResult.put(HttpEnum.NetError, e.getMessage());
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (osw != null) {
                    osw.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                mapResult.put(HttpEnum.NetError, ex.getMessage());
            }
        }
        return mapResult;
    }




}