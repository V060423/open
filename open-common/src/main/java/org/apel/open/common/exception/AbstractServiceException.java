package org.apel.open.common.exception;

/**
 * @author wangbowen
 * @Description 异常接口
 * @Date 2018/6/20 10:15
 */
public interface AbstractServiceException {

    /**
     * 获取异常的状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取异常提示信息
     * @return
     */
    String getErrorMessage();
}
