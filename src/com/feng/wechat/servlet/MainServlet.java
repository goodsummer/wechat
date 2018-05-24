package com.feng.wechat.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.feng.wechat.bean.MsgBean;
import com.feng.wechat.constants.WeChatConstants;
import com.feng.wechat.util.MyUtil;
import com.feng.wechat.util.NetUtil;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(MainServlet.class);
	
	/**
	 * 公众号接入验证
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 接收微信服务器发送请求时传递过来的4个参数
         */
        String signature = request.getParameter("signature");	//微信加密签名signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = request.getParameter("timestamp");	//时间戳
        String nonce = request.getParameter("nonce");	//随机数
        String echostr = request.getParameter("echostr");	//随机字符串
     
        List<String> list = new ArrayList<String>();
        
        list.add(timestamp);
        list.add(nonce);
        list.add(WeChatConstants.TOKEN);
        
        Collections.sort(list);
              
        //加密
        String mySignature = sha1(list.get(0) + list.get(1) + list.get(2));

        //校验签名
        if (mySignature != null && mySignature != "" && mySignature.equals(signature)) {
            System.out.println("签名校验通过。");
            response.getWriter().write(echostr);
        } else {
            System.out.println("签名校验失败.");
        }
	}


	/**
	 * 接收用户事件
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xml = IOUtils.toString(request.getInputStream());
		MsgBean msgBean = (MsgBean) MyUtil.xml2Bean(MsgBean.class, xml);
		String responseString = "";
		
		//关注时
		if("subscribe".equals(msgBean.getEvent())) {
			log.info("一个用户已关注。 " + msgBean.toString());

			String content = "我见青山多妩媚，料青山，见我应如是。";
			responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), content);;		
		}
		
		//取消关注时 
		if("unsubscribe".equals(msgBean.getEvent())){
			Date d = new Date(msgBean.getCreateTime() * 1000);	//因为是php时间戳，所以末尾加三个0
			DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			
			log.info("用户" + msgBean.getFromUserName() + "在 " + format.format(d) + " 取消了关注。");
		}
		
		//接收文字消息
		if("text".equals(msgBean.getMsgType())) {
			for(Map.Entry<String, String> entry : MyUtil.answerMap.entrySet()) {
				if(msgBean.getContent().contains(entry.getKey())) {
					responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), entry.getValue());
					break;
				}
			}	
		}
		
		//接收地理消息
		if("location".equals(msgBean.getMsgType())) {
			String content = "纬度: " + msgBean.getLocation_X() + "\n经度：" + msgBean.getLocation_Y() + "\n地图缩放大小 : " + msgBean.getScale() + "\n地理位置信息: " + msgBean.getLabel();
			responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), content);
		}
		
		//接收图片消息
		if("image".equals(msgBean.getMsgType())) {
			//获取图片类型
			byte[] fielBytes = NetUtil.httpRequestGet(msgBean.getPicUrl());			
			if(fielBytes == null) {
				return;
			}
			
			String fileType = MyUtil.getFileType(fielBytes);
			
			//图片写入	
			File file = new File(WeChatConstants.USR_IMG_PATH + msgBean.getFromUserName() + "_" + msgBean.getCreateTime() + "." + fileType);
			FileOutputStream fileOut = new FileOutputStream(file);		
			fileOut.write(fielBytes);
	
			fileOut.close();
			
			//写入完图片后返回自定义文字
			String content = "你的图片已经被我存在硬盘上了/呲牙";
			responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), content);
		}
		
		//接收语音消息
		if("voice".equals(msgBean.getMsgType())) {
			String recogntion = msgBean.getRecognition();
			if(recogntion.contains("！")) {
				recogntion = recogntion.replace("！", "");
			}
			if(!"".equals(recogntion)) {
				responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), "你说的是" + recogntion + "吧？/呲牙");
			} else {
				responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), "我没听清你说啥/傲慢，再说一次吧");
			}
			
			//获取用户发来的声音，目前还没有权限，注释掉
			//String mediaId = msgBean.getMediaId();
//			String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+ WeChatConstants.ACCESSTOKEN + "&media_id=" + mediaId;
//			
//			//获取声音类型
//			byte[] fielBytes = NetUtil.httpRequest(url);
//			String fileType = MyUtil.getFileType(fielBytes);
//			
//			//声音写入	
//			File file = new File(WeChatConstants.USR_VOICE_PATH + msgBean.getFromUserName() + "_" + msgBean.getCreateTime() + "." + fileType);
//			FileOutputStream fileOut = new FileOutputStream(file);		
//			fileOut.write(fielBytes);
//	
//			fileOut.close();
		}
		
		//接收视频和短视频消息
		if("video".equals(msgBean.getMsgType()) || "shortvideo".equals(msgBean.getMsgType())) {
			responseString = MyUtil.buildAnswerText(msgBean.getFromUserName(), msgBean.getToUserName(), "我不看你发的视频\ue40b");
		}
		
		response.getWriter().write(responseString);
	}

	
	/**
	 * sha1加密
	 * @param str
	 * @return
	 */
    private String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
