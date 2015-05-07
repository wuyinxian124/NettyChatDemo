package edu.scut.wusir.netty4.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class VerifyHandler extends SimpleChannelInboundHandler<String>   {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		super.channelRead(ctx, msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}

	
}
