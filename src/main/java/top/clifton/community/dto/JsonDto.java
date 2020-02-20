package top.clifton.community.dto;

import top.clifton.community.enums.CodeEnum;

/**
 * @author Clifton
 * @create 2020/2/11 - 16:14
 */
public class JsonDto<T> {

    private int code;

    private String message;

    private T data;

    public JsonDto(){

    }

    public JsonDto(int code, String message){
        this.code = code;
        this.message = message;
    }

    public JsonDto(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonDto(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
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
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResponse(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }
}
