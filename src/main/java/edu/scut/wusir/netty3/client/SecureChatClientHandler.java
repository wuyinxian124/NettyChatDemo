package edu.scut.wusir.netty3.client;

import java.util.logging.Logger;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;


/**
 * Handles a client-side channel.
 */
public class SecureChatClientHandler extends SimpleChannelUpstreamHandler {

	private static final Logger logger = Logger
			.getLogger(SecureChatClientHandler.class.getName());

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		String jsonString = (String) e.getMessage();
		Message message = (Message) JsonService.getObjectFromJsonString(
				jsonString, Message.class);
		System.err.println(message.getSendUserName() + ":"
				+ message.getMessage());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		System.out.println("Netty客户端异常,通道Id：" + e.getChannel());
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}
