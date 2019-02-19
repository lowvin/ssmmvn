package com.uaf.chinaums.socket.server.common.utils;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.uaf.nlp.chinaums.api.vo.socket.req.BaseReqVo;
import com.uaf.nlp.chinaums.api.vo.socket.req.HeaderReqVo;
import com.uaf.nlp.chinaums.api.vo.socket.resp.BaseRespVo;
import com.uaf.nlp.chinaums.api.vo.socket.resp.HeaderRespVo;
import com.uaf.nlp.common.ascii.utils.AsciiUtils;
import com.uaf.nlp.common.date.utils.DateUtils;
import com.uaf.nlp.common.log.utils.LogUtil;
import com.uaf.nlp.common.xml.utils.XmlUtils;
import com.uaf.nlp.log.MyLog4j;

/**
 * 文件名：ChinaumsUtils.java
 * 描述：富亿贷请求/响应报文处理工具类
 * 作者：王承
 * 日期：2018年9月7日下午12:34:02
 */
@Component
public class ChinaumsUtils {

	@Autowired
	private ChinaumsProperties chinaumsPropertiesAutowired;
	private static ChinaumsProperties properties;

	@PostConstruct
	public void init() {
		properties = this.chinaumsPropertiesAutowired;
	}

	/**
	 * 富亿贷请求信息统一处理
	 * @param transCode 交易码
	 * @param srcReqId 请求流水号
	 * @param mac  MD5签名串
	 * @param reqBody 请求报文体信息
	 * @return
	 * 作者：王承
	 * 日期：2018年9月9日上午9:31:53
	 */
	public static String getRequestMsg(String transCode, String srcReqId, String mac, Object reqBody) {
		try {
			BaseReqVo<Object> baseReqVo = new BaseReqVo<Object>();
			HeaderReqVo headerReqVo = new HeaderReqVo();
			headerReqVo.setTransCode(transCode);
			headerReqVo.setChannelId(properties.getChannelId());
			headerReqVo.setSrcReqDate(DateUtils.dateToStr(new Date(), DateUtils.STANDAR_FULL_DATE_STYLE));
			headerReqVo.setSrcReqTime(DateUtils.dateToStr(new Date(), DateUtils.STANDAR_TIME_STYLE));
			headerReqVo.setSrcReqId(srcReqId);
			headerReqVo.setMAC(mac);
			baseReqVo.setHeaderReqVo(headerReqVo);
			baseReqVo.setReqBody(reqBody);
			/**将对象转为XML*/
			String reqXml = XmlUtils.convertToXml(baseReqVo);
			/**计算字符串ascii长度*/
			String len = AsciiUtils.strAsciiLength(reqXml);
			return len.concat(reqXml);
		} catch (Exception ex) {
			MyLog4j.textError(MessageFormat.format("构造请求信息异常{0}", LogUtil.ExceptionToString(ex)));
		}
		return null;
	}

	/**
	 * 富亿贷响应信息统一处理
	 * @param headerReqVo 请求报文头信息
	 * @param respBody 响应报文体信息
	 * @param mac MD5签名串
	 * @param respCode 返回代码
	 * @param respMsg 返回信息
	 * @return
	 * 作者：王承
	 * 日期：2018年9月7日下午2:45:28
	 */
	public static String getResponseMsg(HeaderReqVo headerReqVo, Object respBody, String mac, String respCode,
			String respMsg) {
		try {
			BaseRespVo<Object> baseRespVo = new BaseRespVo<Object>();
			HeaderRespVo headerRespVo = new HeaderRespVo();
			/**交易码*/
			headerRespVo.setTransCode(headerReqVo.getTransCode());
			/** 请求系统流水号与返回流水号一致*/
			headerRespVo.setRespReqId(headerReqVo.getSrcReqId());
			headerRespVo.setRespDate(DateUtils.dateToStr(new Date(), DateUtils.STANDAR_FULL_DATE_STYLE));
			headerRespVo.setRespTime(DateUtils.dateToStr(new Date(), DateUtils.STANDAR_TIME_STYLE));
			headerRespVo.setRespCode(respCode);
			headerRespVo.setRespMsg(respMsg);
			headerRespVo.setChannelId(properties.getChannelId());
			if (!StringUtils.isEmpty(mac)) {
				headerRespVo.setMAC(mac);
			}
			baseRespVo.setHeaderRespVo(headerRespVo);
			baseRespVo.setRespBody(respBody);
			/**将对象转为XML*/
			String respXml = XmlUtils.convertToXml(baseRespVo);
			/**计算字符串ascii长度*/
			String len = AsciiUtils.strAsciiLength(respXml);
			MyLog4j.textInfo(
					MessageFormat.format("交易码：{0}，ASCII码长度：{1}，响应信息：{2}", headerReqVo.getTransCode(), len, respXml));
			return len.concat(respXml);
		} catch (Exception ex) {
			MyLog4j.textError(MessageFormat.format("构造响应信息异常{0}", LogUtil.ExceptionToString(ex)));
		}
		return null;
	}

	/**
	 * 封装内部调用返回数据
	 * @param resultCode 返回代号
	 * @param resultInfo 返回信息
	 * @param object 返回对象
	 * @return
	 * 作者：王承
	 * 日期：2018年9月10日下午2:57:27
	 */
	public static Map<String, Object> createMap(String resultCode, String resultInfo, Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("resultCode", resultCode);
		map.put("resultInfo", resultInfo);
		map.put("timestamp", System.currentTimeMillis());
		if (null != object) {
			map.put("data", object);
		}
		return map;
	}

	/**
	 * 获取天天富请求系统流水号
	 * @return
	 * 作者：sz05383
	 * 日期：2017年2月18日下午5:18:29
	 */
	public static String getSrcReqId() {
		return UUID.randomUUID().toString();
	}

}
