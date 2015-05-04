package edu.scut.wusir.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;


/**
 * Simple SSL chat client modified from {@link TelnetClient}.
 */
public class SecureChatClient {

	public static void run(String userName, String host, int port)
			throws IOException {
		// Configure the client.
		// NioClientSocketChannelFactory建客户端的Channel通道对象。
		// 客户端的ClientBootstrap对应ServerBootstrap。
		ClientBootstrap bootstrap = new ClientBootstrap(
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));

		// Configure the pipeline factory.
		bootstrap.setPipelineFactory(new SecureChatClientPipelineFactory());
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(host,
				port));
		Channel channel = future.awaitUninterruptibly().getChannel();
		Message login = new Message();
		login.setSendUserName(userName);
		login.setType(1);
		ChannelFuture future_w = channel.write(JsonService
				.getJsonStringFromObject(login));
		// if(future_w.isSuccess()){
		try {
			new Thread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			System.out.print("请输入消息接收人名称：");
			String receiveUserName = consoleInput.input("消息接收人不能为空");
			System.out.print("请输入消息：");
			String message = consoleInput.input("消息内容不能为空：");
			Message send = new Message();
			send.setSendUserName(userName);
			send.setReceiveUserName(receiveUserName);
			send.setMessage(message);
			send.setType(2);
			String js = JsonService.getJsonStringFromObject(send);
			channel.write(js);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.print("请输入登录名：");
		String userName = consoleInput.input("登录名不能为空");
		String host = "127.0.0.1";
		int port = 8443;
		run(userName, host, port);
	}
}