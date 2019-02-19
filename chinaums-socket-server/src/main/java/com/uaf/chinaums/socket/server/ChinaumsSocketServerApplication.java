package com.uaf.chinaums.socket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uaf.chinaums.socket.server.netty.NettyServer;

/**
 * 文件名：ChinaumsSocketProviderApplication.java
 * 描述：富亿贷socket服务启动类
 * 作者：王承
 * 日期：2018年9月5日下午7:48:12
 */
@SpringBootApplication
public class ChinaumsSocketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChinaumsSocketServerApplication.class, args);
		/**启动netty服务端*/
		NettyServer.runSocket();
	}

}