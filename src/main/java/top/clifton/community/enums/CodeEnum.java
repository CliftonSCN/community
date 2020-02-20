package top.clifton.community.enums;

/**
 * @author Clifton
 * @create 2020/2/11 - 16:24
 */
public enum CodeEnum {

    SERVER_ERROR(500, "服务器异常"),
    USER_NOT_LOGIN(401, "用户未登陆"),
    SUCCESS(200, "success");

    // 成员变量
    private int code;
    private String message;

    // 构造方法
    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}
