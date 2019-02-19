package com.uaf.chinaums.socket.server.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件名：ChinaumsProperties.java
 * 描述：
 * 作者：王承
 * 日期：2018年9月7日下午5:56:45
 */
@Configuration
@ConfigurationProperties(prefix = "chinaums")
public class ChinaumsProperties {

	/**渠道编号*/
	@Value("${chinaums.channelId}")
	private String channelId;

	/**服务端socket端口*/
	@Value("${chinaums.server-port}")
	private Integer serverPort;

	/**客户端socket地址*/
	@Value("${chinaums.client-host}")
	private String clientHost;

	/**客户端socket端口*/
	@Value("${chinaums.client-port}")
	private Integer clientPort;

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getClientHost() {
		return clientHost;
	}

	public void setClientHost(String clientHost) {
		this.clientHost = clientHost;
	}

	public Integer getClientPort() {
		return clientPort;
	}

	public void setClientPort(Integer clientPort) {
		this.clientPort = clientPort;
	}

}
