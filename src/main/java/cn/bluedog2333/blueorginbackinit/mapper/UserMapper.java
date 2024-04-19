package cn.bluedog2333.blueorginbackinit.mapper;

import cn.bluedog2333.blueorginbackinit.model.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
