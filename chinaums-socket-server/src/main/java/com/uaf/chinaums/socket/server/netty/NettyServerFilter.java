package com.uaf.chinaums.socket.server.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

import com.uaf.nlp.sleuth.master.filter.SocketServerFilter;

/**
 * 文件名：NettyServerFilter.java
 * 描述：netty服务端过滤器
 * 作者：王承
 * 日期：2018年9月6日上午10:27:49
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline cp = ch.pipeline();
		/**设置已</UMSFX>为分隔符*/
		ByteBuf delimiter = Unpooled.copiedBuffer("</UMSFX>".getBytes());
		cp.addLast("framer", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, false, delimiter));
		/**解码器*/
		cp.addLast("decoder", new StringDecoder(Charset.forName("GBK")));
		/**编码器*/
		cp.addLast("encoder", new StringEncoder(Charset.forName("GBK")));
		/**服务端业务逻辑*/
		cp.addLast("handler", new NettyServerHandler());
		cp.addLast("filter", new SocketServerFilter());
	}
}
