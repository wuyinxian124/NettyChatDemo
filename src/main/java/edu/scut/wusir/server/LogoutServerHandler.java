package edu.scut.wusir.server;

import java.util.logging.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

/**
 * Handles a server-side channel.
 */
public class LogoutServerHandler extends SimpleChannelUpstreamHandler {
    private static final Logger logger = Logger.getLogger(
            LogoutServerHandler.class.getName());

    static final ChannelGroup channels = new DefaultChannelGroup();

    //连接断开
    public void channelDisconnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        // Unregister the channel from the global channel list
        // so the channel does not receive messages anymore.
        System.out.println("断开一个连接，该连接的channelID:"+e.getChannel().getId());
       channels.remove(e.getChannel());
    }

    
      /**
      事件处理方法由一个ExceptionEvent异常事件调用，这个异常事件起因于Netty的I/O异常或一个处理器实现的内部异常。多数情况下，捕捉
到的异常应当被记录下来，并在这个方法中关闭这个channel通道。当然处理这种异常情况的方法实现可能因你的实际需求而有所不同，例如，在关闭这个连
接之前你可能会发送一个包含了错误码的响应消息。
      */
     public void exceptionCaught(
        ChannelHandlerContext ctx, ExceptionEvent e) {
        System.out.println("服务器端异常，关闭["+e.getChannel().getId()+"]通道");
        e.getChannel().close();
    }
}

