package com.uaf.chinaums.socket.server.biz.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.uaf.chinaums.socket.server.biz.IChinaumsRequestBiz;
import com.uaf.chinaums.socket.server.common.utils.ChinaumsUtils;
import com.uaf.chinaums.socket.server.common.utils.ConstantParamUtils;
import com.uaf.chinaums.socket.server.common.utils.ValidationUtils;
import com.uaf.nlp.chinaums.api.vo.socket.req.BaseReqVo;
import com.uaf.nlp.chinaums.api.vo.socket.req.CustCardConsumeInfoReqVo;
import com.uaf.nlp.chinaums.api.vo.socket.resp.CustCardConsumeInfoRespVo;
import com.uaf.nlp.chinaums.api.vo.socket.resp.CustCardConsumeInfoRespVo.CustCardConsumeInfo;
import com.uaf.nlp.common.json.utils.JsonUtil;
import com.uaf.nlp.common.log.utils.LogUtil;
import com.uaf.nlp.common.security.utils.Md5Utils;
import com.uaf.nlp.common.xml.utils.XmlUtils;
import com.uaf.nlp.log.MyLog4j;

/**
 * 文件名：CustConsumeInfoBizImpl.java
 * 描述：卡消费信息响应报文解析
 * 作者：王承
 * 日期：2018年9月9日上午8:57:38
 */
@Service("chinaumsCustCardConsumeInfoBizImpl")
public class ChinaumsCustCardConsumeInfoBizImpl implements IChinaumsRequestBiz {

	@Override
	public String produceRequestMsg(BaseReqVo<Object> baseReqVo, String reqBody) {
		CustCardConsumeInfoRespVo custCardConsumeInfoRespVo = new CustCardConsumeInfoRespVo();
		String mac = Md5Utils.getMD5(custCardConsumeInfoRespVo.toString());
		try {
			CustCardConsumeInfoReqVo custCardConsumeInfoReqVo = XmlUtils.converyToJavaBean(reqBody,
					CustCardConsumeInfoReqVo.class);

			List<CustCardConsumeInfo> custCardConsumeInfos = new ArrayList<CustCardConsumeInfo>();

			/** 对请求值body进行md5加密 */
			mac = Md5Utils.getMD5(custCardConsumeInfoReqVo.toString());
			/** 验签 */
			if (baseReqVo.getHeaderReqVo().getMAC().equals(mac)) {
				/** 业务参数校验 */
				Map<String, Object> validMap = ValidationUtils.validate(custCardConsumeInfoReqVo);
				if (ConstantParamUtils.RESULT_CODE_FAIL.equals(validMap.get("resultCode"))) {
					return ChinaumsUtils.getResponseMsg(baseReqVo.getHeaderReqVo(), custCardConsumeInfoRespVo, null,
							ConstantParamUtils.RESP_ERROR_CODE, validMap.get("resultInfo").toString());
				}
				for (int i = 1; i < 6; i++) {
					CustCardConsumeInfo cardConsumeInfo = new CustCardConsumeInfo();
					cardConsumeInfo.setStddealmonth("2018091" + i);
					cardConsumeInfo.setStdpanno(custCardConsumeInfoReqVo.getStdpanno());
					cardConsumeInfo.setStdcardattr(i + "");
					cardConsumeInfo.setStdlast3monnum(i + "");
					cardConsumeInfo.setStdlast6monnum(i + "");
					cardConsumeInfo.setStdlastyearnum(i + "");
					cardConsumeInfo.setStdlast3monamt(i + "");
					cardConsumeInfo.setStdlast6monamt(i + "");
					cardConsumeInfo.setStdlast6monmaxamt(i + "");
					cardConsumeInfo.setStdlastyearamt(i + "");
					cardConsumeInfo.setStdlast3monperamt(i + "");
					cardConsumeInfo.setStdlast6monperamt(i + "");
					cardConsumeInfo.setStdlastyearperamt(i + "");
					cardConsumeInfo.setStdtranmons(i + "");
					cardConsumeInfo.setStdbgnum(i + "");
					cardConsumeInfo.setStdcynum(i + "");
					cardConsumeInfo.setStdgynum(i + "");
					cardConsumeInfo.setStdylnum(i + "");
					cardConsumeInfo.setStdzbnum(i + "");
					cardConsumeInfo.setStdddnum(i + "");
					cardConsumeInfo.setStdfdcnum(i + "");
					cardConsumeInfo.setStdqcsxnum(i + "");
					cardConsumeInfo.setStdcsyshhfnum(i + "");
					cardConsumeInfo.setStdglxxnum(i + "");
					cardConsumeInfo.setStdglyynum(i + "");
					cardConsumeInfo.setStdbmnum(i + "");
					cardConsumeInfo.setStdcsnum(i + "");
					cardConsumeInfo.setStddxjdzmnum(i + "");
					cardConsumeInfo.setStdjynum(i + "");
					cardConsumeInfo.setStdhkspnum(i + "");
					cardConsumeInfo.setStdqtkynum(i + "");
					cardConsumeInfo.setStdtlspnum(i + "");
					cardConsumeInfo.setStdbxnum(i + "");
					cardConsumeInfo.setStddxdsjfnum(i + "");
					cardConsumeInfo.setStdggsyjfnum(i + "");
					cardConsumeInfo.setStdzfnum(i + "");
					cardConsumeInfo.setStdbhsdnum(i + "");
					cardConsumeInfo.setStdlxsmpnum(i + "");
					cardConsumeInfo.setStdqtyblshnum(i + "");
					cardConsumeInfo.setStdpfnum(i + "");
					cardConsumeInfo.setStderr38num(i + "");
					cardConsumeInfo.setStderr75num(i + "");
					cardConsumeInfo.setStderr55num(i + "");
					cardConsumeInfo.setStderr51num(i + "");
					cardConsumeInfo.setStderr61num(i + "");
					cardConsumeInfo.setStderr65num(i + "");
					cardConsumeInfo.setStderr34num(i + "");
					cardConsumeInfo.setStderr54num(i + "");
					cardConsumeInfo.setStderr57num(i + "");
					cardConsumeInfo.setStderr62num(i + "");
					cardConsumeInfo.setStdsection1transamt(i + "");
					cardConsumeInfo.setStdsection1transnum(i + "");
					cardConsumeInfo.setStdsection2transamt(i + "");
					cardConsumeInfo.setStdsection2transnum(i + "");
					cardConsumeInfo.setStdsection3transamt(i + "");
					cardConsumeInfo.setStdsection3transnum(i + "");
					cardConsumeInfo.setStdsection4transamt(i + "");
					cardConsumeInfo.setStdsection4transnum(i + "");
					cardConsumeInfo.setStdinvalid_trans_amt(i + "");
					cardConsumeInfo.setStdinvalid_trans_num(i + "");
					cardConsumeInfo.setStdam_trans_amt(i + "");
					cardConsumeInfo.setStdam_trans_num(i + "");
					custCardConsumeInfos.add(cardConsumeInfo);
				}
				custCardConsumeInfoRespVo.setCustCardConsumeInfoList(custCardConsumeInfos);
				/**对响应值body进行md5加密*/
				mac = Md5Utils.getMD5(custCardConsumeInfoRespVo.toString());
				MyLog4j.textInfo("卡消费信息返回结果{0}" + JsonUtil.toJson(custCardConsumeInfoRespVo));
				String result = ChinaumsUtils.getResponseMsg(baseReqVo.getHeaderReqVo(), custCardConsumeInfoRespVo,
						mac, ConstantParamUtils.RESP_SUCCESS_CODE, ConstantParamUtils.RESP_SUCCESS_MSG);
				MyLog4j.textInfo("卡消费信息返回报文" + result);
				return result;
			} else {
				MyLog4j.textError(MessageFormat.format("卡消费信息失败{0}", "验签失败"));
				return ChinaumsUtils.getResponseMsg(baseReqVo.getHeaderReqVo(), custCardConsumeInfoRespVo, mac,
						ConstantParamUtils.RESP_ERROR_CODE, ConstantParamUtils.RESP_FAIL_MAC_MSG);
			}

		} catch (Exception ex) {
			MyLog4j.textError(MessageFormat.format("富亿贷卡消费信息异常{0}", LogUtil.ExceptionToString(ex)));
			return ChinaumsUtils.getResponseMsg(baseReqVo.getHeaderReqVo(), custCardConsumeInfoRespVo, mac,
					ConstantParamUtils.RESP_ERROR_CODE, ConstantParamUtils.RESP_FAIL_MSG);
		}
	}
}
