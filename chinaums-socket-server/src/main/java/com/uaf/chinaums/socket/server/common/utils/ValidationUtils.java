package com.uaf.chinaums.socket.server.common.utils;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.HibernateValidator;

import com.google.gson.JsonObject;
import com.uaf.nlp.common.log.utils.LogUtil;
import com.uaf.nlp.log.MyLog4j;

/**
 * 
 * 文件名：ValidationUtils.java
 * 描述：参数校验工具类
 * 作者：王承
 * 日期：2018年9月10日下午3:04:08
 */
public class ValidationUtils {

	/**
	 * 使用hibernate的注解来进行验证
	 * failFast：true  快速失败返回模式    false 普通模式 
	 */
	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false)
			.buildValidatorFactory().getValidator();

	/**
	 * 参数校验
	 * @param obj
	 * @return
	 * 作者：王承
	 * 日期：2018年9月10日下午3:03:45
	 */
	public static <T> Map<String, Object> validate(T obj) {
		try {
			Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
			if (constraintViolations.size() > 0) {
				JsonObject objPro = new JsonObject();
				for (ConstraintViolation<T> c : constraintViolations) {
					objPro.addProperty(c.getPropertyPath().toString(), c.getMessage());
				}
				MyLog4j.textError(MessageFormat.format("参数校验{0}", objPro.toString()));
				return ChinaumsUtils.createMap(ConstantParamUtils.RESULT_CODE_FAIL,
						ConstantParamUtils.VALIDATE_ERROR_MESSAGE, null);
			}
		} catch (Exception ex) {
			MyLog4j.textError(MessageFormat.format("参数校验异常{0}", LogUtil.ExceptionToString(ex)));
			return ChinaumsUtils.createMap(ConstantParamUtils.RESULT_CODE_FAIL,
					ConstantParamUtils.VALIDATE_ABNORMAL_MESSAGE, null);
		}
		return ChinaumsUtils.createMap(ConstantParamUtils.RESULT_CODE_SUCC, ConstantParamUtils.VALIDATE_SUCCESS_MESSAGE,
				null);
	}
}
