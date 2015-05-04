package edu.scut.wusir.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * Simple SSL chat server modified from {@link TelnetServer}.
 */
public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() {
	    //NioServerSocketChannelFactory 是一个创建和管理Channel通道及其相关资源的工厂接口，
	    //它处理所有的I/O请求并产生相应的I/O
	    //ChannelEvent通道事件。
	
	    //ServerBootstrap设置服务的帮助类。
	    //你甚至可以在这个服务中直接设置一个Channel通道。然而请注意，这是一个繁琐的过程，
	    //大多数情况下并不需要这样做。
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Configure the pipeline factory.
        bootstrap.setPipelineFactory(new ServerPipelineFactory());

        //请注意我们在配置选项里添加的"child."前缀。
        //这意味着这个配置项仅适用于我们接收到的通道实例，而不
        //是ServerSocketChannel实例
        bootstrap.setOption("child.tcpNoDelay", true);  
        bootstrap.setOption("child.keepAlive", true);  

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(port));
        System.out.println("Netty服务器端接收消息服务开启成功,端口号为："+port);
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8443;
        }
        new Server(port).run();
    }
}



