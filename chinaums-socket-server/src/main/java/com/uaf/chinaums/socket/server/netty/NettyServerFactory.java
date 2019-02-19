package com.uaf.chinaums.socket.server.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.uaf.chinaums.socket.server.biz.IChinaumsRequestBiz;
import com.uaf.chinaums.socket.server.common.utils.ConstantParamUtils;
import com.uaf.nlp.chinaums.api.vo.socket.req.BaseReqVo;

/**
 * 文件名：NettyServerFactory.java
 * 描述：netty服务端业务处理抽象工厂
 * 作者：王承
 * 日期：2018年9月6日下午3:52:46
 */
@Component
public class NettyServerFactory {

	@Autowired
	private ApplicationContext applicationContext;

	public IChinaumsRequestBiz produce(BaseReqVo<Object> baseReqVo) {
		/**交易代码*/
		String transCode = baseReqVo.getHeaderReqVo().getTransCode();
		if (ConstantParamUtils.QUERY_CUST_CARD_CONSUME_INFO.equals(transCode)) {
			/**卡消费信息*/
			return (IChinaumsRequestBiz) this.applicationContext.getBean("chinaumsCustCardConsumeInfoBizImpl");
		}
		return null;
	}
}
