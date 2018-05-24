package com.feng.wechat.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.feng.wechat.bean.AccessTokenBean;
import com.feng.wechat.constants.WeChatConstants;
import com.feng.wechat.util.NetUtil;


@WebServlet(
	name = "OnStartLoadServlet",
	urlPatterns = {"/OnStartLoadServlet"},
	loadOnStartup = 1
)
public class OnStartLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(OnStartLoadServlet.class);
	
	@Override
	public void init() throws ServletException{	
		
		new Thread(){
			@Override
			public void run() {
				System.out.println("请求accesstoken的线程已启动");
				log.info("请求accesstoken的线程已启动......");
				
				//创建图片存储文件夹
				File imgDir = new File(WeChatConstants.USR_IMG_PATH);
				if(!imgDir.exists()) {
					imgDir.mkdirs();
					System.out.println("图片存储文件夹创建成功");
					log.info("图片存储文件夹创建成功......");
				} else {
					log.info("图片存储文件夹已存在......");
				}
				
				//创建音频存储文件夹
				File voiceDir = new File(WeChatConstants.USR_VOICE_PATH);
				if(!voiceDir.exists()) {
					voiceDir.mkdirs();
					System.out.println("音频存储文件夹创建成功");
					log.info("音频存储文件夹创建成功......");
				} else {
					log.info("音频存储文件夹已存在......");
				}
				
				//启动accesstoken查询的线程
				while(true) {
					AccessTokenBean acessTokenBean = getAccessToken();
					
					if(acessTokenBean != null) {
						log.info("请求accesstoken成功," + acessTokenBean.toString());		
						WeChatConstants.ACCESSTOKEN = acessTokenBean.getAccessToken();
						
						try {
							Thread.sleep(acessTokenBean.getExpiredTime() * 1000);
						} catch (InterruptedException e) {
							log.error("休眠请求accesstoken失败");
						}						
					} else {
						try {
							Thread.sleep(WeChatConstants.ACCESSTOKEN_RETRY_TIME);
						} catch (InterruptedException e) {
							log.error("请求accesstoken重试失败");
						}
					}
				}
			}
		}.start();	
	}
	
	/**
	 * 获取accesstoken
	 */
	public AccessTokenBean getAccessToken() {
		String appId = WeChatConstants.APP_ID;
		String appSecret = WeChatConstants.APPSECRET;
		
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" +
				"&appid=" + appId +
				"&secret=" + appSecret;
		
		try {
			String responseString = "";
			try {
				responseString = new String(NetUtil.httpRequestGet(url) , "utf-8");
			} catch(Exception e) {
				return null;
			}
			
			System.out.println(responseString);
			
			JSONObject json = JSONObject.parseObject(responseString);
			
			String accessToken = json.getString("access_token");
			if(accessToken == null) {
				int errcode = json.getIntValue("errcode");
				String errmsg = json.getString("errmsg");
				
				log.error("获取accesstoken失败,[errcode: " + errcode + ", errmsg: " + errmsg);
				return null;
			}
			
			AccessTokenBean accessTokenBean = new AccessTokenBean();		
			accessTokenBean.setAccessToken(json.getString("access_token"));
			accessTokenBean.setExpiredTime(json.getIntValue("expires_in"));
			
			return accessTokenBean;
		} catch (Exception e) {
			log.error("获取accesstoken失败，内部错误", e);
		}
		
		return null;
	}
}
