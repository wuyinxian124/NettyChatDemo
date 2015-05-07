package edu.scut.wusir.javawebsocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;


public class ClientMain {

	private String HOST;


	//private final String HOST = ""ws://222.201.139.159:8877"";
	//private final int PORT = 0;
	
	public ClientMain(String HOST){
		this.HOST = HOST;
	}
	
	
	public void start0(String user,String verify) throws InterruptedException, URISyntaxException{
		 WebsocketC client = new WebsocketC( new URI(HOST), new Draft_10() );
		 client.connect();
		 TimeUnit.SECONDS.sleep(3);
		 client.send(user+":"+verify+":"+"homewtb");
		 int i = 0;
		 while(i<10){
			 client.send("chatroom1##0##"+user +" send a msg "+i);
			 i++;
		 }
		 
	}
}
