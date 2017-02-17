package com.zjdex.core.utils.hanxin;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * 用于http/https请求
 * 
 * @author LIUAOZ
 * @since 2016年12月29日 下午5:39:56
 * @version 1.0
 */
public class HttpUtil {

    private static final String ACCEPT = "accept";
    private static final String CONNECTION = "connection";
    private static final String USER_AGENT = "user-agent";

    private static SslContextUtils sslContextUtils = new SslContextUtils();

    /**
     * 使用Get方式获取数据
     *
     * @param url URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
     * @param charset
     * @return
     */
    public static String sendGetNew(String url, String charset) throws IOException{
        String result = "";
        URL realUrl =new URL(url);
        // 打开和URL之间的连接
        URLConnection connection = null;
        connection = realUrl.openConnection();

        try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset))){
            // 设置通用的请求属性
            connection.setRequestProperty(ACCEPT, "*/*");
            connection.setRequestProperty(CONNECTION, "Keep-Alive");
            connection.setRequestProperty(USER_AGENT,
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch(IOException e){
            throw e;
        }
        return result;
    }


    /**
     * 使用Get方式获取数据
     * 
     * @param url URL包括参数，http://HOST/XX?XX=XX&XXX=XXX
     * @param charset
     * @return
     */
    public static String sendGet(String url, String charset) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty(ACCEPT, "*/*");
            connection.setRequestProperty(CONNECTION, "Keep-Alive");
            connection.setRequestProperty(USER_AGENT,
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，字符串形式数据
     * 
     * @param url 请求地址
     * @param param 请求数据
     * @param charset 编码方式
     */
    public static String sendPostUrl(String url, String param, String charset) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(ACCEPT, "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST请求，Map形式数据
     * 
     * @param url 请求地址
     * @param param 请求数据
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    public static String sendPost(String url, Map<String, String> param, String charset)
            throws UnsupportedEncodingException {

        StringBuffer buffer = new StringBuffer();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                buffer.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), charset)).append("&");

            }
        }
        buffer.deleteCharAt(buffer.length() - 1);

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty(ACCEPT, "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(buffer);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 使用post方式，发送xml格式数据
     * 
     * @param postUrl
     * @param xmlData
     * @return
     */
    public static String sendXMLDataByPost(String postUrl, String xmlData) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(postUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

            if (httpConn instanceof HttpsURLConnection) {
                sslContextUtils.initHttpsConnect((HttpsURLConnection) httpConn);
            }

            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setRequestProperty("Content-type", "text/xml");
            httpConn.setConnectTimeout(60000);
            httpConn.setReadTimeout(60000);
            // 发送请求
            httpConn.getOutputStream().write(xmlData.getBytes("utf-8"));
            httpConn.getOutputStream().flush();
            httpConn.getOutputStream().close();
            // 获取输入流
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "utf-8"));
            char[] buf = new char[1024];
            int length = 0;
            while ((length = reader.read(buf)) > 0) {
                sb.append(buf, 0, length);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



    /**
     * 读取输入流，获取其字节数组
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] read(InputStream in) throws IOException {
        byte[] buf = new byte[1024];
        int length = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            baos.write(buf, 0, length);
        }
        baos.flush();
        return baos.toByteArray();
    }

}
