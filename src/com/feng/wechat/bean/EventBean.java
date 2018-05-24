package com.feng.wechat.bean;

public class EventBean {
	/**
	 * 开发者微信号
	 */
	private String ToUserName;
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String FromUserName;
	/**
	 * 消息创建时间 （整型）
	 */
	private long CreateTime;
	/**
	 * 消息类型 
	 */	
	private String MsgType;
	/**
	 * 事件类型  subscribe(订阅),unsubscribe(取消订阅), LOCATION(地理位置), SCAN(已关注用户扫二维码时), CLICK(点击菜单), VIEW(点击菜单跳转)
	 */
	private String Event;
	/**
	 * 事件KEY值
	 * 扫描二维码关注时：qrscene_为前缀，后面为二维码的参数值
	 * 已关注用户扫描二维码时：是一个32位无符号整数，即创建二维码时的二维码scene_id
	 * 点击菜单时：自定义菜单接口中KEY值
	 * 点击菜单跳转：设置的跳转URL
	 */
	private String EventKey;
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	private String Ticket;
	/**
	 * 地理位置纬度
	 */
	private double Latitude;
	/**
	 * 地理位置经度
	 */
	private double Longitude;
	/**
	 * 地理位置精度
	 */
	private double Precision;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getPrecision() {
		return Precision;
	}
	public void setPrecision(double precision) {
		Precision = precision;
	}
	
	@Override
	public String toString() {
		return "EventBean [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", Event=" + Event + ", EventKey=" + EventKey
				+ ", Ticket=" + Ticket + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + ", Precision=" + Precision + "]";
	}
}
