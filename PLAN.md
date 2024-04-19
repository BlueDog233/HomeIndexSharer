# 蓝泉后端初始项目
## 用户管理
* 鉴权
* 登录(图形验证码)
* 注册(手机验证码)
* 找回密码
* 修改密码
* 修改个人信息
* 管理端统一修改
* 封号/解封
>接口设计
> 
> User add(User user)
> 
> boolean del(List<Integer> ids)
> 
> User get(int id)
> 
> List<User> list()
> 
> User update(User user)
> 
> List<User> listpage(int page, int size)
> 
> int total
> 
> String register(DTO dto)
> 
> String login(DTO dto)
## 支付系统
>接口设计 
> 
> String getqrCodeToPay
> 
> 