package com.feng.wechat.bean;

public class AccessTokenBean {
	/**
	 * accessToken
	 */
	private String accessToken;
	/**
	 * 过期时间
	 */
	private int expiredTime;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(int expiredTime) {
		this.expiredTime = expiredTime;
	}
	
	@Override
	public String toString() {
		return "AccessToken [accessToken=" + accessToken + ", expiredTime=" + expiredTime + "]";
	}
}	
