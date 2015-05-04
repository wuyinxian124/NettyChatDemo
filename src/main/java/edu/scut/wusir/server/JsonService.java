package edu.scut.wusir.server;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;

public class JsonService {

	public static Object getObjectFromJsonString(String mes ,Class<Message> msg){
		return JSON.parseObject(mes, msg);
	}
	
	public static String getJsonStringFromObject(Message message){
		
		return JSON.toJSONString(message);
	}
}
