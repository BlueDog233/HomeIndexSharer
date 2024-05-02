package cn.bluedog2333.blueorginbackinit.mapper;

import cn.bluedog2333.blueorginbackinit.model.pojo.StoreUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
* @author BlueDog
* @description 针对表【storeuser】的数据库操作Mapper
* @createDate 2024-04-24 14:35:22
* @Entity cn.bluedog2333.blueorginbackinit.model.pojo.Storeuser
*/
public interface StoreuserMapper extends BaseMapper<StoreUser> {
    @Update("update storeuser set photo_data=#{photoData} where id=#{id}")
    void uploadPhoto(StoreUser user);
    @Update("UPDATE storeuser SET " +
            "avatar = #{avatar}, " +
            "username = #{username}, " +
            "is_publish = #{isPublish}, " +
            "`style` = #{style}, " +
            "text_data = #{textData}, " +
            "photo_data = #{photoData}, " +
            "template = #{template}, " +
            "email = #{email}, " +
            "stars = #{stars} " +
            "WHERE id = #{id}")
    int updatePojo(StoreUser user);
}




