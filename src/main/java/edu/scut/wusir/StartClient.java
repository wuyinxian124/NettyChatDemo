package edu.scut.wusir;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;

import edu.scut.wusir.javawebsocket.WebsocketC;


public class StartClient {

	public static void main(String[] args) throws URISyntaxException {

		 WebsocketC client = new WebsocketC( new URI("ws://localhost:8877"), new Draft_17() );
		 client.connect();
		 client.verifyUser("user1", "verify1", "homewtb");
		 int i = 0;
		 while(i<10){
			 client.send("chatroom1##0##"+"user1 send a msg "+i);
			 i++;
		 }
		 
		 
	}

}
