package com.uaf.chinaums.socket.server.common.utils;

/**
 * 
 * 文件名：ConstantParamUtils.java
 * 描述：常量参数Utils
 * 作者：王承
 * 日期：2018年9月6日下午3:48:52
 */
public class ConstantParamUtils {

	/**服务返回成功编码*/
	public final static String RESULT_CODE_SUCC = "000000";
	/**服务返回失败编码*/
	public final static String RESULT_CODE_FAIL = "111111";

	/**返回码-成功*/
	public final static String RESP_SUCCESS_CODE = "99999999";

	/**返回码-失败*/
	public final static String RESP_ERROR_CODE = "00000000";

	/**返回信息-成功*/
	public final static String RESP_SUCCESS_MSG = "成功";

	/**返回信息-处理异常*/
	public final static String RESP_FAIL_MSG = "处理异常";

	/**返回信息-验签失败*/
	public final static String RESP_FAIL_MAC_MSG = "验签失败";

	/**参数校验返回-参数校验不通过*/
	public final static String VALIDATE_ERROR_MESSAGE = "参数校验不通过";

	/**参数校验返回-参数校验通过*/
	public final static String VALIDATE_SUCCESS_MESSAGE = "参数校验通过";

	/**参数校验返回-参数校验异常*/
	public final static String VALIDATE_ABNORMAL_MESSAGE = "参数校验异常";

	/**准入规则校验交易代码*/
	public final static String CHECK_ACCESS_RULE_TRANSCODE = "IFPP014";

	/**授信申请*/
	public final static String CREDIT_APPLICATION = "IFPP000";

	/**授信额度详细信息查询*/
	public final static String QUERY_CREDIT_LIMIT_DETAILS_INFO = "IFPP001";

	/**授信额度列表信息查询*/
	public final static String QUERY_CREDIT_LIMIT_LIST_INFO = "IFPP002";

	/**卡消费信息查询*/
	public final static String QUERY_CUST_CARD_CONSUME_INFO = "IFPP118";

	/**贷款支用信息列表查询*/
	public final static String QUERY_LOAN_PAYMENTS_LIST_INFO = "IFPP007";

	/**贷款支用详细信息查询*/
	public final static String QUERY_LOAN_PAYMENTS_DETAILS_INFO = "IFPP008";

	/**预览还款计划查询*/
	public final static String PREVIEW_LOAN_PAYMENTS_PLAN_INFO = "YLC001";

	/**还款明细查询*/
	public final static String QUERY_LOAN_PAYMENT_DETAILS_INFO = "IFPP011";

	/** 还款计划查询 **/
	public final static String QUERY_LOAN_PAYMENTS_PLAN_INFO = "IFPP015";

	/** 提前还款 **/
	public final static String REPAYMENT = "IFPP010";

	/** 提前还款试算 **/
	public final static String REPAYMENT_CALCULATION = "IFPP009";

	/**授信/贷款合同生成*/
	public final static String LOAN_CONTRACT_PRODUCE = "IFPP003";

	/**授信/贷款合同签订*/
	public final static String LOAN_CONTRACT_SIGN = "IFPP004";

	/**合同文本查询*/
	public final static String LOAN_CONTRACT_QUERY = "IFPP005";

	/**申请状态推送*/
	public final static String LOAN_APPLY_RESULT_NOTICE = "IFPP114";

	/** 放/还款账户卡号变更*/
	public final static String LOAN_CHANGE_BANKNO = "IFPP013";
}
