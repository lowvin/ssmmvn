package com.uaf.chinaums.socket.server.biz;

import com.uaf.nlp.chinaums.api.vo.socket.req.BaseReqVo;

/**
 * 文件名：INettyServerBiz.java 描述：处理富亿贷接口数据 作者：王承 日期：2018年9月6日上午11:13:57
 */
public interface IChinaumsRequestBiz {

	/**
	 * 富亿贷请求报文处理
	 * 
	 * @param baseReqVo
	 *            富亿贷请求报文头
	 * @param reqBody
	 *            富亿贷请求报文体
	 * @return 作者：lowvin 日期：2018年9月6日下午4:15:04
	 */
	public String produceRequestMsg(BaseReqVo<Object> baseReqVo, String reqBody);

}
