package edu.scut.wusir.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 这里假设所有用户放在内存中的Map里面
 * 
 * @author a
 * 
 */
public class UserDB {
	private static HashMap<String, UserInfo> userInfoMap = new HashMap<String, UserInfo>();

	public static boolean addUser(UserInfo userInfo) {
		boolean flag = false;
		try {
			userInfoMap.put(userInfo.getUserName(), userInfo);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static UserInfo getUserInfoByUserName(String userName) {
		return (UserInfo) userInfoMap.get(userName);
	}

	public static Integer getUserChannelIdByUserName(String userName) {
		UserInfo userInfo = getUserInfoByUserName(userName);
		if (userInfo == null || userInfo.getUserChannelId() == null) {
			return 0;
		} else {
			return userInfo.getUserChannelId();
		}
	}

	public static HashMap<String, UserInfo> getAllUserMap() {
		return userInfoMap;
	}

	public static boolean removeUserByUserName(String userName) {
		boolean flag = false;
		try {
			userInfoMap.remove(userName);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean removeUserByChannelId(Integer channelId) {
		boolean flag = false;
		try {
			HashMap<String, UserInfo> allUser = getAllUserMap();
			Iterator it = allUser.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String userName = (String) entry.getKey();// 得么关键字
				UserInfo value = (UserInfo) entry.getValue();// 得到值
				if (channelId == value.getUserChannelId()) {
					allUser.remove(userName);
				}
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static boolean isExitUserByUsername(String userName) {
		boolean flag = false;
		Integer channelId = getUserChannelIdByUserName(userName);
		if (channelId == null || channelId == 0 || "".equals(channelId)) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}
}
