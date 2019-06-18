package com.control.page.util;


import lombok.Data;

/**
 * 描述:
 *
 * @author partner
 * @create 2018-10-29 11:44
 */
@Data
public class JsonResult<T> {
    private ResultCodeEnum code;
    private String message;
    private Integer httpCode;
    private T data;

    public JsonResult() {
        this.code = ResultCodeEnum.SUCCESS;
        this.message = ResultCodeEnum.SUCCESS.getStatusStr();
        this.httpCode = ResultCodeEnum.SUCCESS.getStatusCode();
        this.data = null;
    }

    public JsonResult(T data) {
        this.code = ResultCodeEnum.SUCCESS;
        this.message = ResultCodeEnum.SUCCESS.getStatusStr();
        this.httpCode = ResultCodeEnum.SUCCESS.getStatusCode();
        this.data = data;
    }

    public JsonResult(ResultCodeEnum resultCode, String message, Integer httpCode) {
        this.code = resultCode;
        this.message = message;
        this.httpCode = httpCode;
        this.data = null;
    }

    public JsonResult(ResultCodeEnum resultCode, String message, Integer httpCode, T data) {
        this.code = resultCode;
        this.message = message;
        this.httpCode = httpCode;
        this.data = data;
    }


    public static JsonResult ok() {
        return new JsonResult(ResultCodeEnum.SUCCESS, ResultCodeEnum.SUCCESS.getStatusStr(), ResultCodeEnum.SUCCESS.getStatusCode());
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(ResultCodeEnum.SUCCESS, ResultCodeEnum.SUCCESS.getStatusStr(), ResultCodeEnum.SUCCESS.getStatusCode(), data);
    }

    public static JsonResult ok(String msg ,Object data) {
        return new JsonResult(ResultCodeEnum.SUCCESS, msg, ResultCodeEnum.SUCCESS.getStatusCode(), data);
    }

    public static JsonResult dataExits() {
        return new JsonResult(ResultCodeEnum.SUCCESS_IS_HAVE, "数据已存在！", ResultCodeEnum.SUCCESS_IS_HAVE.getStatusCode(), ResultCodeEnum.SUCCESS_IS_HAVE.getStatusCode());
    }

    public static JsonResult dataExits(String retMsg) {
        return new JsonResult(ResultCodeEnum.SUCCESS_IS_HAVE, retMsg, ResultCodeEnum.SUCCESS_IS_HAVE.getStatusCode(), ResultCodeEnum.SUCCESS_IS_HAVE.getStatusCode());
    }

    public static JsonResult notUpdateJsonResult() {
        return new JsonResult(ResultCodeEnum.NOT_UPDATE, "数据没有被更新", ResultCodeEnum.NOT_UPDATE.getStatusCode());
    }

    public static JsonResult notUpdateJsonResult(String retMsg) {
        return new JsonResult(ResultCodeEnum.NOT_UPDATE, retMsg, ResultCodeEnum.NOT_UPDATE.getStatusCode());
    }

    public static JsonResult returnNotParamJsonResult() {
        return new JsonResult(ResultCodeEnum.PARAMS_ERROR, "参数错误！", ResultCodeEnum.PARAMS_ERROR.getStatusCode());
    }

    public static JsonResult returnNotParamJsonResult(String returnMsg) {
        return new JsonResult(ResultCodeEnum.PARAMS_ERROR, returnMsg, ResultCodeEnum.PARAMS_ERROR.getStatusCode());
    }

    public static JsonResult returnFailJsonResult() {
        return new JsonResult(ResultCodeEnum.FAIL, "操作失败！", ResultCodeEnum.FAIL.getStatusCode());
    }

    public static JsonResult returnFailJsonResult(String returnMsg) {
        return new JsonResult(ResultCodeEnum.FAIL, returnMsg, ResultCodeEnum.FAIL.getStatusCode());
    }

    public static JsonResult returnExceptionJsonResult() {
        return new JsonResult(ResultCodeEnum.EXCEPTION, "操作异常！", ResultCodeEnum.EXCEPTION.getStatusCode());
    }

    public static JsonResult returnExceptionJsonResult(String returnMsg) {
        return new JsonResult(ResultCodeEnum.EXCEPTION, returnMsg, ResultCodeEnum.EXCEPTION.getStatusCode());
    }

    public static JsonResult returnExceptionJsonResult(String returnMsg, Object data) {
        return new JsonResult(ResultCodeEnum.EXCEPTION, returnMsg, ResultCodeEnum.EXCEPTION.getStatusCode(), data);
    }
}
