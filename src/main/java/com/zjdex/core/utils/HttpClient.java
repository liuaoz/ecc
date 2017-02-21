package com.zjdex.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.Map;

/**
 * @author LIUAOZ
 * @version 1.0
 * @since 2016年12月29日 下午2:10:30
 */
public class HttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    private URL url;
    private int connectionTimeout;
    private int readTimeOut;
    private String result;

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 构造http请求客户端
     *
     * @param url
     * @param connectionTimeout
     * @param readTimeOut
     */
    public HttpClient(String url, int connectionTimeout, int readTimeOut) {
        try {
            this.url = new URL(url);
            this.connectionTimeout = connectionTimeout;
            this.readTimeOut = readTimeOut;
        } catch (MalformedURLException e) {
            LOGGER.error("error ==>" + e.getMessage());
        }
    }

    /**
     * 发起请求
     *
     * @param data
     * @param encoding
     * @return
     * @throws Exception
     */
    public int send(Map<String, String> data, String encoding) throws Exception {
        try {
            HttpURLConnection httpURLConnection = createConnection(encoding);
            if (null == httpURLConnection) {
                throw new Exception("创建联接失败");
            }
            requestServer(httpURLConnection, getRequestParamString(data, encoding), encoding);

            this.result = response(httpURLConnection, encoding);
            LOGGER.info(new StringBuilder().append("返回报文:[").append(this.result).append("]").toString());
            return httpURLConnection.getResponseCode();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 读取服务器响应新，将结果写到连接输出流中
     *
     * @param connection
     * @param message
     * @param encoder
     * @throws Exception
     */
    private void requestServer(URLConnection connection, String message, String encoder) throws Exception {
        PrintStream out = null;
        try {
            connection.connect();
            out = new PrintStream(connection.getOutputStream(), false, encoder);
            out.print(message);
            out.flush();

        } catch (Exception e) {
            throw e;
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

    /**
     * 获取响应报文内容
     *
     * @param connection
     * @param encoding
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws Exception
     */
    private String response(HttpURLConnection connection, String encoding) throws URISyntaxException, IOException, Exception {
        InputStream in = null;
        StringBuilder sb = new StringBuilder(1024);
        try {
            if (200 == connection.getResponseCode()) {
                in = connection.getInputStream();
                sb.append(new String(read(in), encoding));
            } else {
                in = connection.getErrorStream();
                sb.append(new String(read(in), encoding));
            }
            LOGGER.info(new StringBuilder().append("HTTP Return Status-Code:[").append(connection.getResponseCode()).append("]").toString());

            return sb.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != connection) {connection.disconnect();}
        }
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

    /**
     * 创建连接
     *
     * @param encoding
     * @return
     * @throws ProtocolException
     */
    private HttpURLConnection createConnection(String encoding) throws ProtocolException {
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) this.url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        httpURLConnection.setConnectTimeout(this.connectionTimeout);
        httpURLConnection.setReadTimeout(this.readTimeOut);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        // 设置请求头header
        httpURLConnection.setRequestProperty("Content-type", new StringBuilder().append("application/x-www-form-urlencoded;charset=").append(encoding).toString());

        httpURLConnection.setRequestMethod("POST");
        if ("https".equalsIgnoreCase(this.url.getProtocol())) {
            HttpsURLConnection husn = (HttpsURLConnection) httpURLConnection;
            husn.setSSLSocketFactory(new BaseHttpSSLSocketFactory());
            husn.setHostnameVerifier(new BaseHttpSSLSocketFactory.TrustAnyHostnameVerifier());
            return husn;
        }
        return httpURLConnection;
    }

    /**
     * 组装请求参数<br>
     * 格式为key1=value1&key2=value2<br>
     * 注意：会对value进行url encode处理
     *
     * @param requestParam
     * @param coder
     * @return
     */
    private String getRequestParamString(Map<String, String> requestParam, String coder) {
        if ((null == coder) || ("".equals(coder))) {
            coder = "UTF-8";
        }
        StringBuffer sf = new StringBuffer("");
        String reqstr = "";
        if ((null != requestParam) && (0 != requestParam.size())) {
            for (Map.Entry<String, String> en : requestParam.entrySet()) {
                try {
                    sf.append(new StringBuilder().append((String) en.getKey()).append("=").append(
                        (null == en.getValue()) || ("".equals(en.getValue())) ?
                            "" :
                            URLEncoder.encode(en.getValue(), coder)).append("&").toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            reqstr = sf.substring(0, sf.length() - 1);
        }
        LOGGER.info(new StringBuilder().append("请求报文:[").append(reqstr).append("]").toString());
        return reqstr;
    }


}
