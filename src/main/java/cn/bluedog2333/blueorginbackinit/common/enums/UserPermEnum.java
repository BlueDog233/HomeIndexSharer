package cn.bluedog2333.blueorginbackinit.common.enums;

public enum UserPermEnum {
    NOLOGIN("未登录", 0),
    USER("用户", 1),
    ADMIN("管理员", 2);

    UserPermEnum(String userPerm, int i) {
        this.userPerm=userPerm;
        this.i=i;
    }
    //获得权限组名称
    private String userPerm;
    //获得值
    private int i;
    public String getUserPerm() {
        return userPerm;
    }

    public int getI() {
        return i;
    }


}
