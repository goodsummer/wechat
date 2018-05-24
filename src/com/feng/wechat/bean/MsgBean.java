package com.feng.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class MsgBean {
	public static enum MessageType {
        TEXT, // 文本消息
        IMAGE, // 图片消息
        VOICE, // 语音消息
        VIDEO, // 视频消息
        SHORTVIDEO, // 小视频消息
        LOCATION, // 地理位置消息
        LINK, // 链接消息
        EVENT// 事件消息
    }
	
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;// text image voice video shortvideo location link
    private long MsgId;// 消息id，64位整型
    //文本消息内容
    private String Content;
    // image voice video:
    private String MediaId;// 媒体id，可以调用多媒体文件下载接口拉取数据。
    // image特有字段
    private String PicUrl;// 图片链接
    // voice特有字段
    private String Format;// 语音格式，如amr，speex等
    // video shortvideo特有字段
    private String ThumbMediaId;// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    private String Recognition; //语音辨识结果，utf编码
    private Image Image;
    private Voice Voice;
    private Video Video;
    private Music Music; 
    // location 特有字段
    private double Location_X;// 地理位置纬度
    private double Location_Y;// 地理位置经度
    private int Scale;// 地图缩放大小
    private String Label;// 地理位置信息
    // link 特有字段
    private String Title;// 消息标题
    private String Description;// 消息描述
    private String Url;// 消息链接
    
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
    public class Image {
		/**
    	 * 通过素材管理接口上传多媒体文件，得到的id。
    	 */
    	private String MediaId;
    	public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
    }
    
    public class Voice {
		/**
    	 * 通过素材管理接口上传多媒体文件，得到的id。
    	 */
    	private String MediaId;
    	public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
    }
    public class Video {
		private String MediaId;	//通过素材管理接口上传多媒体文件，得到的id
    	private String Title;	//视频消息的标题
    	private String Description;	//视频消息的描述
    	public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
    }
    public class Music {
    	private String Title;	//音乐标题
    	private String Description;	//音乐描述
    	private String MusicUrl;	//音乐链接
    	private String HQMusicUrl;	//高质量音乐链接，WIFI环境优先使用该链接播放音乐
    	private String ThumbMediaId;	//缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getMusicUrl() {
			return MusicUrl;
		}
		public void setMusicUrl(String musicUrl) {
			MusicUrl = musicUrl;
		}
		public String getHQMusicUrl() {
			return HQMusicUrl;
		}
		public void setHQMusicUrl(String hQMusicUrl) {
			HQMusicUrl = hQMusicUrl;
		}
		public String getThumbMediaId() {
			return ThumbMediaId;
		}
		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
    }
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
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public String getRecognition() {
		return Recognition;
	}
	public void setRecognition(String recognition) {
		Recognition = recognition;
	}
	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		Image = image;
	}
	public Voice getVoice() {
		return Voice;
	}
	public void setVoice(Voice voice) {
		Voice = voice;
	}
	public Video getVideo() {
		return Video;
	}
	public void setVideo(Video video) {
		Video = video;
	}
	public Music getMusic() {
		return Music;
	}
	public void setMusic(Music music) {
		Music = music;
	}
	public double getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(double location_X) {
		Location_X = location_X;
	}
	public double getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(double location_Y) {
		Location_Y = location_Y;
	}
	public int getScale() {
		return Scale;
	}
	public void setScale(int scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
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
		return "MsgBean [ToUserName=" + ToUserName + ", FromUserName="
				+ FromUserName + ", CreateTime=" + CreateTime + ", MsgType="
				+ MsgType + ", MsgId=" + MsgId + ", Content=" + Content
				+ ", MediaId=" + MediaId + ", PicUrl=" + PicUrl + ", Format="
				+ Format + ", ThumbMediaId=" + ThumbMediaId + ", Recognition="
				+ Recognition + ", Image=" + Image + ", Voice=" + Voice
				+ ", Video=" + Video + ", Music=" + Music + ", Location_X="
				+ Location_X + ", Location_Y=" + Location_Y + ", Scale="
				+ Scale + ", Label=" + Label + ", Title=" + Title
				+ ", Description=" + Description + ", Url=" + Url + ", Event="
				+ Event + ", EventKey=" + EventKey + ", Ticket=" + Ticket
				+ ", Latitude=" + Latitude + ", Longitude=" + Longitude
				+ ", Precision=" + Precision + "]";
	}
}
