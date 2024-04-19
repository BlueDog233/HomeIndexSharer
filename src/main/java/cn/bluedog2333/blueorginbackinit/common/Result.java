package cn.bluedog2333.blueorginbackinit.common;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;


    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success(T data) {
        return new Result<T>(1, "", data);
    }
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(1, message, data);
    }
    public static <T> Result<T> error(String message) {
        return new Result<T>(0, message, null);
    }

}
