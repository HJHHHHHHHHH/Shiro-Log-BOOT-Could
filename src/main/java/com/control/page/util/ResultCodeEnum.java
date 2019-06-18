package com.control.page.util;

/**
 * 描述:
 * 结果类
 *
 * @author partner
 * @create 2018-10-29 11:39
 */
public enum ResultCodeEnum {
    /** 成功 */
    SUCCESS(200, "成功"),

    /** 操作失败 */
    FAIL(205, "操作失败"),

    /** 数据已存在 */
    SUCCESS_IS_HAVE(208, "数据已存在"),

    /**
     * 数据没更新
     */
    NOT_UPDATE(300, "数据没更新"),

    /** 没有结果 */
    NOT_DATA(911, "没有结果"),

    /** 没有登录 */
    NOT_LOGIN(600, "没有登录"),

    /** 发生异常 */
    EXCEPTION(401, "发生异常"),

    /** 系统错误 */
    SYS_ERROR(402, "系统错误"),

    /** 参数错误 */
    PARAMS_ERROR(403, "参数错误 "),

    /** 不支持或已经废弃 */
    NOT_SUPPORTED(410, "不支持或已经废弃"),

    /** AuthCode错误 */
    INVALID_AUTHCODE(444, "无效的AuthCode"),

    /** 太频繁的调用 */
    TOO_FREQUENT(445, "太频繁的调用"),

    /** 未知的错误 */
    UNKNOWN_ERROR(499, "未知错误"),

    /** 未设置方法 */
    NOT_METHOD(404, "未设置方法"),

    DML_ERROR(500,"执行数据操作失败"),
    ;

    private ResultCodeEnum(Integer statusCode, String statusStr){
        this.statusCode = statusCode;
        this.statusStr = statusStr;
    }

    private Integer statusCode;
    private String statusStr;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusStr() {
        return statusStr;
    }
}
