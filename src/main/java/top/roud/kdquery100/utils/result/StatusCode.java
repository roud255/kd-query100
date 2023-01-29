package top.roud.kdquery100.utils.result;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/6
 * @version:
 */
public enum StatusCode {
    SUCCESS("200","成功"),
    ERROR("500","服务器错误"),
    FAIL("501","查无结果"),
    ACCOUNT_CHECK_FAIL("301","账号校验失败");

    private String code;

    public String getCode() {
        return code;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    StatusCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
