package com.feng.wechat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.feng.wechat.bean.MsgBean;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * 自定义工具类
 * @author zjf
 */
public class MyUtil {
	
	private static Logger log = Logger.getLogger(MyUtil.class);

	/**
	 * 自动回答的map。 key：发来的消息包含的文字   value：回复的内容
	 */
	public static final Map<String, String> answerMap = new HashMap<String, String>(); 
	
	/**
	 * 判断文件类型的map。 key:文件头  value：后缀名
	 */
	public final static Map<String, String> fileTypeMap = new HashMap<String, String>(); 
	
	static {
			answerMap.put("傻逼", "你才是傻逼/微笑 ");
			answerMap.put("傻吊", "你才是傻吊/微笑 ");
			answerMap.put("傻屌", "你才是傻屌/微笑 ");
			answerMap.put("想吃啥？", "牛肉串/酷 ");
			answerMap.put("睡觉", "不能睡/嘘");
			answerMap.put("???", "/疑问");
			answerMap.put("who are u?", "。。。。/心碎");
	};
	
	static {
		fileTypeMap.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)     
        fileTypeMap.put("89504e470d0a1a0a0000", "png"); //PNG (png)     
        fileTypeMap.put("47494638396126026f01", "gif"); //GIF (gif)     
        fileTypeMap.put("49492a00227105008037", "tif"); //TIFF (tif)     
        fileTypeMap.put("424d228c010000000000", "bmp"); //16色位图(bmp)     
        fileTypeMap.put("424d8240090000000000", "bmp"); //24位位图(bmp)     
        fileTypeMap.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)     
        fileTypeMap.put("41433130313500000000", "dwg"); //CAD (dwg)     
        fileTypeMap.put("3c21444f435459504520", "html"); //HTML (html)
        fileTypeMap.put("3c21646f637479706520", "htm"); //HTM (htm)
        fileTypeMap.put("48544d4c207b0d0a0942", "css"); //css
        fileTypeMap.put("696b2e71623d696b2e71", "js"); //js
        fileTypeMap.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)     
        fileTypeMap.put("38425053000100000000", "psd"); //Photoshop (psd)     
        fileTypeMap.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)       
        fileTypeMap.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样     
        fileTypeMap.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图     
        fileTypeMap.put("5374616E64617264204A", "mdb"); //MS Access (mdb)      
        fileTypeMap.put("252150532D41646F6265", "ps");     
        fileTypeMap.put("255044462d312e350d0a", "pdf"); //Adobe Acrobat (pdf)   
        fileTypeMap.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同  
        fileTypeMap.put("464c5601050000000900", "flv"); //flv与f4v相同  
        fileTypeMap.put("00000020667479706d70", "mp4"); 
        fileTypeMap.put("49443303000000002176", "mp3"); 
        fileTypeMap.put("000001ba210001000180", "mpg"); //     
        fileTypeMap.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同    
        fileTypeMap.put("52494646e27807005741", "wav"); //Wave (wav)  
        fileTypeMap.put("52494646d07d60074156", "avi");  
        fileTypeMap.put("4d546864000000060001", "mid"); //MIDI (mid)   
        fileTypeMap.put("504b0304140000000800", "zip");    
        fileTypeMap.put("526172211a0700cf9073", "rar");   
        fileTypeMap.put("235468697320636f6e66", "ini");   
        fileTypeMap.put("504b03040a0000000000", "jar"); 
        fileTypeMap.put("4d5a9000030000000400", "exe");//可执行文件
        fileTypeMap.put("3c25402070616765206c", "jsp");//jsp文件
        fileTypeMap.put("4d616e69666573742d56", "mf");//MF文件
        fileTypeMap.put("3c3f786d6c2076657273", "xml");//xml文件
        fileTypeMap.put("494e5345525420494e54", "sql");//xml文件
        fileTypeMap.put("7061636b616765207765", "java");//java文件
        fileTypeMap.put("406563686f206f66660d", "bat");//bat文件
        fileTypeMap.put("1f8b0800000000000000", "gz");//gz文件
        fileTypeMap.put("6c6f67346a2e726f6f74", "properties");//bat文件
        fileTypeMap.put("cafebabe0000002e0041", "class");//bat文件
        fileTypeMap.put("49545346030000006000", "chm");//bat文件
        fileTypeMap.put("04000000010000001300", "mxp");//bat文件
        fileTypeMap.put("504b0304140006000800", "docx");//docx文件
        fileTypeMap.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
        fileTypeMap.put("6431303a637265617465", "torrent");
        
          
        fileTypeMap.put("6D6F6F76", "mov"); //Quicktime (mov)  
        fileTypeMap.put("FF575043", "wpd"); //WordPerfect (wpd)   
        fileTypeMap.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)     
        fileTypeMap.put("2142444E", "pst"); //Outlook (pst)      
        fileTypeMap.put("AC9EBD8F", "qdf"); //Quicken (qdf)     
        fileTypeMap.put("E3828596", "pwl"); //Windows Password (pwl)         
        fileTypeMap.put("2E7261FD", "ram"); //Real Audio (ram)     
	}
	
	/**
	 * xml转换为bean
	 * @param clazz 要转换的bean class
	 * @param request HttpServletRequest
	 * @return
	 */
	public static Object xml2Bean(Class<?> clazz, String xml) {
		XStream xs = new XStream(new DomDriver());
		
		Object obj = null;
		
		try {
			obj = clazz.newInstance();	
			xs.alias("xml", clazz); 
			
			obj = xs.fromXML(xml);
		} catch (Exception e) {
			log.error("转换xml为bean失败!" ,e);
		}
		
		return obj;
	}
	
	/**
	 * bean转换为xml，值为null的不转换 转换的头的名称请在bean中注解
	 * @param obj
	 * @return
	 */
	public static String bean2Xml(Object obj) {
		XStream xs = new XStream(new DomDriver());

		xs.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
		String xml = xs.toXML(obj);
		
		return xml;
	}
	
	/**
	 * 创建一个文字消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content	消息的内容
	 * @return 用于返回给微信服务器的xml
	 */
	public static String buildAnswerText(String toUserName, String fromUserName, String content) {
		MsgBean msgBean = new MsgBean();
		
		msgBean.setToUserName(toUserName);
		msgBean.setFromUserName(fromUserName);
		msgBean.setCreateTime(System.currentTimeMillis());
		msgBean.setMsgType(MsgBean.MessageType.TEXT.name().toLowerCase());
		msgBean.setContent(content);

		//bean转化为xml
		return bean2Xml(msgBean);
	}
	
	//素材管理还没有权限
//	public static void getFileByMediaIdFromWeXin(String mediaId) throws ClientProtocolException, IOException {
//		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=" + mediaId;
//		
//		byte[] fileBytes = NetUtil.httpRequest(url);
//		byte[] byteTen = new byte[10];
//		
//		for(int i=0; i<10; i++) {
//			byteTen[i] = fileBytes[i];
//		}
//		
//		String hexString = bytesToHexString(byteTen);
//		String type = "";
//		
//		for(Entry<String, String> entry : fileTypeMap.entrySet()) {
//			if(entry.getKey().equals(hexString)) {
//				type = entry.getValue();
//			}
//		}
//		
//		File f = new File("");
//		FileOutputStream fo = new FileOutputStream(f);
//		fo.write(fileBytes);
//		
//		fo.close();
//	}
	
    /**
     * 根据字节得到十六进制值
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * 获取文件类型
     * @param 文件byte数组
     * @return
     */
    public static String getFileType(byte[] b) {
    	
		byte[] byteTen = new byte[10];
		
		for(int i=0; i<10; i++) {
			byteTen[i] = b[i];
		}
		
		String hexString = bytesToHexString(byteTen);
		String type = "";
		
		for(Entry<String, String> entry : fileTypeMap.entrySet()) {
			if(entry.getKey().equals(hexString)) {
				type = entry.getValue();
				break;
			}
		}
		
		return type;
    }
}
