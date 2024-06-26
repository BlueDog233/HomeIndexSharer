package cn.bluedog2333.blueorginbackinit.controller;

import cn.bluedog2333.blueorginbackinit.annotation.NeedPerm;
import cn.bluedog2333.blueorginbackinit.common.Result;
import cn.bluedog2333.blueorginbackinit.common.enums.UserPermEnum;
import cn.bluedog2333.blueorginbackinit.common.errors.CommonError;
import cn.bluedog2333.blueorginbackinit.model.dto.login.*;
import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import cn.bluedog2333.blueorginbackinit.service.UserService;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.ContextUtil;
import cn.bluedog2333.blueorginbackinit.utils.beanutils.VerifyCodeUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * > User add(User user)
 * >
 * > boolean del(List<Integer> ids)
 * >
 * > User get(int id)
 * >
 * > List<User> list()
 * >
 * > User update(User user)
 * >
 * > List<User> listpage(int page, int size)
 * >
 * > int total
 */

@CrossOrigin
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContextUtil contextUtil;
/*  @PostMapping("")
    public Result<User> add(@RequestBody User user){
        userService.saveOrUpdate(user);
        return Result.success(userService.get(user.getUsername()));
    }
    */

    @NeedPerm(UserPermEnum.ADMIN)
    @PostMapping("/del")
    public Result<Boolean> del(@RequestBody List<Integer> ids){
        userService.removeByIds(ids);
        return Result.success(true);
    }

    @NeedPerm(UserPermEnum.ADMIN)

    @PutMapping("/update")
    public Result<Boolean> update(User user){
        userService.updateById(user);
        return Result.success(true);
    }

    @NeedPerm(UserPermEnum.ADMIN)

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable int id){
        User user = userService.getById(id);
        if(user!=null){
            return Result.success(user);
        }
        return Result.error("没有此用户");
    }
    @NeedPerm(UserPermEnum.ADMIN)


    @GetMapping("")
    public Result<List<User>> list(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(User::getId).orderByDesc(User::getUpdateTime);
        List<User> list = userService.list(lambdaQueryWrapper);
        return Result.success(list);

    }
    @NeedPerm(UserPermEnum.ADMIN)

    @GetMapping("/page")
    public Result<Page> page(int page,int pageSize){
        Page<User> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(User::getId);
        userService.page(pageinfo,lambdaQueryWrapper);
        return Result.success(pageinfo);
    }



//    @PostMapping("/size")
//    public Result<Long> total(String keyword){
//        if (ObjectUtil.isNull(keyword)) {
//            keyword="";
//        }
//        return Result.success(userService.total(keyword));
//    }
//
//    @PostMapping("/sendverifyimg/{nickname}")
//    public Result<String> sendVerifyImg(@PathVariable("nickname") String nickname) throws IOException {
//        return Result.success(userService.sendVerifyImg(nickname));
//    }
//    @PostMapping("/sendverifycode/{email}")
//    public Result sendVerifyCode(@PathVariable("email") String email){
//        userService.sendVerifyCode(email);
//        return Result.success(null);
//    }
//    @PostMapping("/modifypassword")
//    public Result<String> modifyPassword(ChangePasswordDTO dto){
//        if (BeanUtil.hasNullField(dto)) {
//            return Result.error(CommonError.PARAMETER_ERROR.toString());
//        }
//        String token=userService.modifyPassword(dto);
//        return Result.success(token);
//    }
//    @PostMapping("/refindpassword")
//    public Result<String> refindPassword(RefindPasswordDTO dto){
//        if(BeanUtil.hasNullField(dto)){
//            throw new CustomException(CommonError.PARAMETER_ERROR.toString());
//        }
//        String str=userService.refindPassword(dto);
//        return Result.success(str);
//    }



}
