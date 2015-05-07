package edu.scut.wusir.javawebsocket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

public class WebsocketC extends WebSocketClient {

	
	public WebsocketC(URI serverUri, Draft draft) {
		super(serverUri, draft);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		
	}

	@Override
	public void onMessage(String message) {
		System.out.println("收到信息 ，执行 onMessage" + message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("关闭 ，执行 onClose");
	}

	@Override
	public void onError(Exception ex) {
		System.out.println("有异常 ，执行 onError");
	}

	public void verifyUser(String username,String verifyCode,String url){
		send(username+"##"+verifyCode+"##"+url);
	}
}
