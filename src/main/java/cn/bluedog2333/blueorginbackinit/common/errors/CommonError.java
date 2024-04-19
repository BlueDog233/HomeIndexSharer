package cn.bluedog2333.blueorginbackinit.common.errors;

public enum CommonError {
    NOT_LOGIN("未登录", 1001), NO_PERMISSION("权限不足", 1000), UNKOWN_ERROR("未知错误", 1002), INVALID_TOKEN("token无效", 1003), INVALID_TOKEN_EXPIRED("token过期", 1004), PARAMETER_ERROR("参数错误,请检查参数格式", 1005), USERNAME_EXIST("用户名已存在", 1006), PASSWORD_NOT_EQUAL("两次密码输入不一致", 1007),
    LENGTH_TO_LONG("太长了",1008 ), USERNAME_NOEXIST("账号不存在",1009 ), INVALID_VERIFY_CODE("无效验证码",1010 );

    private String msg;
    private int code;

    CommonError(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return code + ":" + msg;
    }
}
