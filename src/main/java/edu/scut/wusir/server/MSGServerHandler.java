package edu.scut.wusir.server;

import java.util.logging.Logger;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

//import com.cn.netty.example.bean.Message;
//import com.cn.netty.example.bean.UserInfo;
//import com.cn.netty.example.mapDB.ServerChannelGroup;
//import com.cn.netty.example.mapDB.UserDB;
//import com.cn.netty.example.util.JsonService;
/**
 * Handles a server-side channel.
 */
public class MSGServerHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = Logger
			.getLogger(MSGServerHandler.class.getName());

	@Override
	// 当服务器接收到消息后 会触发该事件
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String jsonString = (String) e.getMessage();
		System.out.println("===================" + jsonString);
		Message object;
		object = (Message) JsonService.getObjectFromJsonString(
				jsonString.toString(), Message.class);
		// type是1的 是登录类型
		if (object.getType() == 1) {
			// 登录请求
			String userName = object.getSendUserName();
			System.out.println("用户[" + userName + "]登录请求...");
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(userName);
			userInfo.setUserChannelId(e.getChannel().getId());
			// 将登录用户信息写入内存中
			UserDB.addUser(userInfo);
			Message message = new Message();
			message.setSendUserName("系统消息");
			message.setMessage("[" + userName + "]用户登录成功");
			String sendMessage = JsonService.getJsonStringFromObject(message);
			e.getChannel().write(sendMessage);
		} else if (object.getType() == 2) {
			// 服务器接收的是消息
			Message message = object;
			System.out.println("用户[" + message.getSendUserName() + "]发送消息,目标是["
					+ message.getReceiveUserName() + "]");
			if (UserDB.isExitUserByUsername(message.getReceiveUserName())) {
				// 消息接收人在线
				UserInfo userInfo = UserDB.getUserInfoByUserName(message
						.getReceiveUserName());
				System.err.println("用户名：" + userInfo.getUserName() + "  通道Id："+ userInfo.getUserChannelId());
				Channel channel = ServerChannelGroup
						.getChannelByChannelId(userInfo.getUserChannelId());
				String sendMessage = JsonService
						.getJsonStringFromObject(message);
				channel.write(sendMessage);
			} else {
				// 消息接收人不在线,发送系统消息告诉对方目标用户不在线
				message.setSendUserName("系统消息");
				message.setMessage("用户[" + message.getReceiveUserName()+ "]不在线，请您稍后重新联系他");
				String sendMessage = JsonService
						.getJsonStringFromObject(message);
				e.getChannel().write(sendMessage);
			}
		} else {
			// ToDo 其他消息
		}
	}

	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("服务器端异常，关闭[" + e.getChannel().getId() + "]通道");
		e.getChannel().close();
	}
}
