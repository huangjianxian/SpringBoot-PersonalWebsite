package com.jontyhua.web.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConstraintViolationExceptionHandler
 * @Author: JONTY HUA
 * @Date: 2019/3/27 22:38
 * @Description: 处理异常的，比如邮件有几种异常情况，在这里集中处理
 */
public class ConstraintViolationExceptionHandler {
    /**
     * 获取批量异常信息
     * @param e
     * @return
     */
    public static String getMessage(ConstraintViolationException e) {
        List<String> msgList = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            msgList.add(constraintViolation.getMessage());
        }
        String messages = StringUtils.join(msgList.toArray(), ";");
        return messages;
    }
}
