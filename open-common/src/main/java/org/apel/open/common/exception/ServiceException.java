package org.apel.open.common.exception;

/**
 * @author wangbowen
 * @Description 业务异常
 * @Date 2018/6/20 10:15
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(AbstractServiceException exception) {
        super(exception.getErrorMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getErrorMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
