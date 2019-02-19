package com.uaf.chinaums.socket.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.MessageFormat;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uaf.chinaums.socket.server.biz.IChinaumsRequestBiz;
import com.uaf.chinaums.socket.server.common.utils.ChinaumsUtils;
import com.uaf.chinaums.socket.server.common.utils.ConstantParamUtils;
import com.uaf.chinaums.socket.server.common.utils.ValidationUtils;
import com.uaf.nlp.chinaums.api.vo.socket.req.BaseReqVo;
import com.uaf.nlp.common.log.utils.LogUtil;
import com.uaf.nlp.common.xml.utils.XmlUtils;
import com.uaf.nlp.log.MyLog4j;

/**
 * 文件名：NettyServerHandler.java
 * 描述：netty服务端业务处理类
 * 作者：王承
 * 日期：2018年9月6日上午10:47:21
 */
@Component
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

	@Autowired
	private NettyServerFactory nettyServerFactory;

	private static NettyServerHandler nettyServerHandler;

	@PostConstruct
	public void init() {
		nettyServerHandler = this;
	}

	/**固定报文头长度*/
	public static final int HEAD_LENGTH = 6;

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) {
		try {
			if (StringUtils.isNotBlank(msg)) {
				/**响应报文*/
				String respMsg = null;
				MyLog4j.textInfo(MessageFormat.format("富亿贷请求报文{0}", msg));
				msg = msg.substring(HEAD_LENGTH, msg.length());
				/**处理富亿贷请求报文*/
				BaseReqVo<Object> baseReqVo = XmlUtils.converyToJavaBean(msg, BaseReqVo.class);
				/**截取请求报文体*/
				msg = "<ReqBody>" + msg.split("<ReqBody>")[1].split("</ReqBody>")[0] + "</ReqBody>";
				/**业务参数校验*/
				Map<String, Object> validMap = ValidationUtils.validate(baseReqVo.getHeaderReqVo());
				if (ConstantParamUtils.RESULT_CODE_FAIL.equals(validMap.get("resultCode"))) {
					respMsg = ChinaumsUtils.getResponseMsg(baseReqVo.getHeaderReqVo(), null, null,
							ConstantParamUtils.RESP_ERROR_CODE, validMap.get("resultInfo").toString());
				} else {
					/**调用netty工厂*/
					IChinaumsRequestBiz chinaums = nettyServerHandler.nettyServerFactory.produce(baseReqVo);
					respMsg = chinaums.produceRequestMsg(baseReqVo, msg);
				}
				/**netty响应处理*/
				ctx.writeAndFlush(respMsg);
			} else {
				MyLog4j.textError("富亿贷请求报文为空");
			}
		} catch (Exception ex) {
			MyLog4j.textError(MessageFormat.format("netty服务端业务处理异常{0}", LogUtil.ExceptionToString(ex)));
		}
	}

}
