package cn.bluedog2333.blueorginbackinit.common.enums;

public enum UserPermEnum {
    NOLOGIN("未登录", 0),
    USER("用户", 1),
    VIP("VIP", 2),
    ADMIN("管理员", 3);

    UserPermEnum(String userPerm, int i) {
        this.userPerm=userPerm;
        this.i=i;
    }
    private String userPerm;
    private int i;
    public String getUserPerm() {
        return userPerm;
    }

    public int getI() {
        return i;
    }


}
