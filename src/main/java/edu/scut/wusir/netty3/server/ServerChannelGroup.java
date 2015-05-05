package edu.scut.wusir.netty3.server;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;

public class ServerChannelGroup {
	private static ChannelGroup group = new DefaultChannelGroup();

	public static boolean addChannelByChannel(Channel channel) {
		boolean flag = false;
		try {
			group.add(channel);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public static Channel getChannelByChannelId(Integer channelId) {
		return group.find(channelId);
	}

	public static boolean romoveChannelByChannelId(Integer channelId) {
		boolean flag = false;
		try {
			group.remove(getChannelByChannelId(channelId));
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
