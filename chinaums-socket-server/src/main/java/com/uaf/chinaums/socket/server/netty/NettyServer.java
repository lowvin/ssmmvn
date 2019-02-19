package com.uaf.chinaums.socket.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaf.chinaums.socket.server.common.utils.ChinaumsProperties;
import com.uaf.nlp.common.ip.utils.IpUtil;
import com.uaf.nlp.common.log.utils.LogUtil;
import com.uaf.nlp.log.MyLog4j;

/**
 * 文件名：NettyServer.java
 * 描述：netty服务端
 * 作者：王承
 * 日期：2018年9月6日上午10:22:29
 */
@Component
public class NettyServer {

	@Autowired
	private ChinaumsProperties chinaumsPropertiesAutowired;
	private static ChinaumsProperties properties;

	@PostConstruct
	public void init() {
		properties = this.chinaumsPropertiesAutowired;
	}

	/**通过nio方式来接收连接和处理连接*/
	private static EventLoopGroup group = new NioEventLoopGroup();
	private static ServerBootstrap bootstrap = new ServerBootstrap();

	/**
	 * 启动socket
	 * 作者：王承
	 * 日期：2018年9月6日上午10:26:16
	 */
	public static void runSocket() {
		try {
			bootstrap.group(group);
			bootstrap.channel(NioServerSocketChannel.class);
			/**设置过滤器*/
			bootstrap.childHandler(new NettyServerFilter());
			/**服务端绑定IP及端口监听*/
			ChannelFuture futrue = bootstrap.bind(IpUtil.getServerIpAddr(), properties.getServerPort()).sync();
			// 监听服务器关闭监听
			futrue.channel().closeFuture().sync();
		} catch (InterruptedException ex) {
			MyLog4j.textError(MessageFormat.format("netty服务端启动异常{0}", LogUtil.ExceptionToString(ex)));
		} finally {
			/**关闭EventLoopGroup，释放掉所有资源包括创建的线程  */
			group.shutdownGracefully();
		}
	}

}
