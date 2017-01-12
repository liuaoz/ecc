package com.zjdex.core.utils.shujt;

/**
* 通过java处理时调用API的方法(示例)<br>
*
* @author datatang
* @version 1.0
* @create date 2015/11
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
/**
* 使用java发送GET请求获取数据
* @author datatang
*
*/
public class WebApitest {
	static Logger log = Logger.getLogger(WebApitest.class);

	/**
	 *
	 */
	public WebApitest() {
		// TODO Auto-generated constructor stub
	}

	// 测试主程序
	public static void main(String[] args) throws Exception {
		// str为加密前的参数字符串 王娟.431121199003108447
		String str = "idCardName=何传周&idCardCode=420922195509046035";
		System.out.println(DesEncrypter.encrypt(str,"c8d7ac55b22faa229a7fb9243b017f31"));

		// 连接url地址
//		String strUrl = "http://apidata.datatang.com/data/credit/validateIDCard";
		String strUrl = "http://apidatatest.datatang.com/data/credit/validateIDCard";
		// dtkey 通过页面申请的API KEY。(必须项目)
		String strKey = "c8d7ac55b22faa229a7fb9243b017f31";
		// rettype 需要返回的格式（支持JSON）(必须项目)
		String strRettype = "json";
		// 各API需要参数（详细参考画面-各API参数不同）
		String strparam = "";
		// 例如
		strparam = "encryptParam="+DesEncrypter.encrypt(str,"strKey");
		// 访问URL地址
		String url = strUrl + "?apikey=" + strKey + "&rettype=" + strRettype
				+ "&" + strparam;
		System.out.println("URL:"+url);
		try {
			String res = readByGet(url);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过GET请求调用url获取结果
	 * 
	 * @param inUrl
	 *            请求url
	 * @throws IOException
	 * @return String 获取的结果
	 */
	private static String readByGet(String inUrl) throws IOException {
		StringBuffer sbf = new StringBuffer();
		String strRead = null;
		// 模拟浏览器
		String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 "
				+ "(KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
		// 连接URL地址
		URL url = new URL(inUrl);
		// 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型,
		// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置连接访问方法及超时参数
		connection.setRequestMethod("GET");
		connection.setReadTimeout(30000);
		connection.setConnectTimeout(30000);
		connection.setRequestProperty("User-agent", userAgent);
		// 进行连接，但是实际上get request要在下一句的connection.getInputStream()函数中才会真正发到 服务器
		connection.connect();
		// 取得输入流，并使用Reader读取
		InputStream is = connection.getInputStream();
		// 读取数据编码处理
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		while ((strRead = reader.readLine()) != null) {
			sbf.append(strRead);
			sbf.append("\r\n");
		}
		reader.close();
		// 断开连接
		connection.disconnect();
		return sbf.toString();
	}
}
