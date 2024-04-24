package cn.bluedog2333.blueorginbackinit.common;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private int type;//仅有ai回复消息才有

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
    public static JSONObject aiResponse(String json){
        return JSONUtil.parseObj(json);
    }
}
