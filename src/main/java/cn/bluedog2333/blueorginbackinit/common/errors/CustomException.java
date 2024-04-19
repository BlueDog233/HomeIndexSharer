package cn.bluedog2333.blueorginbackinit.common.errors;


public class CustomException extends RuntimeException {
    private String msg="未知错误";

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }
    public CustomException(){
        super();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}