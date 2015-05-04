package edu.scut.wusir.server;

import static org.jboss.netty.channel.Channels.*;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class ServerPipelineFactory implements
        ChannelPipelineFactory {

    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = pipeline();

        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // and then business logic.
        pipeline.addLast("login", new LoginServerHandler());
        pipeline.addLast("msgReceive", new MSGServerHandler());
        pipeline.addLast("logout", new LogoutServerHandler());
//        pipeline.addLast("handler", new SecureChatServerHandler());
        return pipeline;
    }
}
