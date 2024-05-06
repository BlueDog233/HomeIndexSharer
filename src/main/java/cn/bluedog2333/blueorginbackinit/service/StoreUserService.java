package cn.bluedog2333.blueorginbackinit.service;

import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author BlueDog
* @description 针对表【storeuser】的数据库操作Service
* @createDate 2024-04-24 14:35:22
*/
public interface StoreUserService extends IService<StoreUser> {
    String uploadInfo(Info info);

    String aiMixed();

    void useTemplate(Integer templateId);
}
