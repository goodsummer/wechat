package com.feng.wechat.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class NetUtil {
	
	private static final Logger log = Logger.getLogger(NetUtil.class);
	
	/**
	 * get方式请求
	 * @param url
	 * @return respone Entity的byte数组
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static byte[] httpRequestGet(String url) throws ClientProtocolException, IOException {		
	 	HttpClient client = HttpClients.createDefault();
	    HttpGet get = new HttpGet(url);                     
    	HttpResponse response = client.execute(get);

    	int statusCode = response.getStatusLine().getStatusCode();
    	
    	if(statusCode != HttpStatus.SC_OK) {
    		log.error("http request 连接失败! http状态码为：" + statusCode); 
    		return null;
    	}
    	
    	byte[] responseBytes = EntityUtils.toByteArray(response.getEntity());
    	get.releaseConnection();	// 释放连接
    	
		return responseBytes;
	}
	
	/**
	 * http post请求
	 * @param url	post url
	 * @param json	参数的json数据
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static byte[] httpRequestPost(String url, JSONObject json) throws ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		
        StringEntity entity = new StringEntity(json.toString(),"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");    
        entity.setContentType("application/json");
		post.setEntity(entity);
		
		HttpResponse response = client.execute(post);
    	int statusCode = response.getStatusLine().getStatusCode();
    	
    	if(statusCode != HttpStatus.SC_OK) {
    		log.error("http post 连接失败! http状态码为：" + statusCode); 
    		return null;
    	}
    	
    	byte[] responseBytes = EntityUtils.toByteArray(response.getEntity());
    	post.releaseConnection();	// 释放连接
    	
		return responseBytes;
	}
	
	//语意理解接口  未开放
//	public static void main(String[] args) throws ClientProtocolException, IOException {
//		String url = "https://api.weixin.qq.com/semantic/semproxy/search?access_token=" + "nUVrBXIJtdG9rsTYAS4fe6bJ55u1PEppdqlQRXrS_0PxIPASfRKNQLXcypC3z8TpPA3LBw1GqV6E1tMbxGgHOgU3WkdtxUox77s-j3YvJYaTR0ytr-giXN95tuZKTYWaOCGhAIAKYC";
//		
//		JSONObject json = new JSONObject();
//		json.put("query", "查一下明天从北京到上海的南航机票");
//		json.put("city", "北京");
//		json.put("category", "flight,hotel");
//		json.put("appid", WeChatConstants.APP_ID);	
//		json.put("uid", "oOZdJw4fxuffB9ht5N2Uyzdu-2Io");
//		
//		byte[] responseByte = NetUtil.httpRequestPost(url, json);
//		System.out.println(IOUtils.toString(responseByte, "utf-8"));
//	}
}
