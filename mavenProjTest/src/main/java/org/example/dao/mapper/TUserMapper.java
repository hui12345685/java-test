package org.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.example.dao.proj.TUser;

public interface TUserMapper extends BaseMapper<TUser> {

    List<TUser> queryByAge(Map<String, Object> filters);
}
