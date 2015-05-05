package edu.scut.wusir.netty3.server;

import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import java.util.logging.Logger;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

//import com.cn.netty.example.mapDB.ServerChannelGroup;
//import com.cn.netty.example.mapDB.UserDB;

/**
 * Handles a server-side channel.
 */
public class LoginServerHandler extends SimpleChannelUpstreamHandler {
	private static final Logger logger = Logger
			.getLogger(LoginServerHandler.class.getName());

	static final ChannelGroup channels = new DefaultChannelGroup();

	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		Channel ch = e.getChannel();
		System.out.println("服务器端建立一个连接,通道Id：" + ch.getId());
		ServerChannelGroup.addChannelByChannel(e.getChannel());
		super.channelConnected(ctx, e);
	}

	public void handleUpstream(ChannelHandlerContext arg0, ChannelEvent arg1)
			throws Exception {
		super.handleUpstream(arg0, arg1);
	}

	/**
	 * 事件处理方法由一个ExceptionEvent异常事件调用，
	 * 这个异常事件起因于Netty的I/O异常或一个处理器实现的内部异常。
	 * 多数情况下，捕捉到的异常应当被记录下来，并在这个方法中关闭这个channel通道。 
	 * 当然处理这种异常情况的方法实现可能因你的实际需求而有所不同，
	 * 例如，在关闭这个连接之前你可能会发送一个包含了错误码的响应消息。
	 */
     public void exceptionCaught(
        ChannelHandlerContext ctx, ExceptionEvent e) {
        System.out.println("服务器端异常，关闭["+e.getChannel().getId()+"]通道,并让该用户退出");
        e.getCause().printStackTrace();
        UserDB.removeUserByChannelId(e.getChannel().getId());
        e.getChannel().close();
    }
}