package cn.lettle.pubresource.mapper;

//import cn.lettle.pubresource.entity.Wall;

import cn.lettle.pubresource.entity.Wall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WallMapper extends BaseMapper<Wall> {

}

