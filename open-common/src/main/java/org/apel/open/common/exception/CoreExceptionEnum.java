package org.apel.open.common.exception;

/**
 * @author wangbowen
 * @Description 核心业务异常
 * @Date 2018/6/20 10:15
 */
public enum CoreExceptionEnum implements AbstractServiceException {

    /**
     * token异常
     */
    NO_CURRENT_USER(700, "当前没有登录的用户"),
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),
    PERMISSION_ERROR(800, "没有访问该资源的权限");

    CoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getErrorMessage() {
        return message;
    }
}
