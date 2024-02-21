package cn.lettle.pubresource.mapper;
import cn.lettle.pubresource.entity.LibraryLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

@Mapper
public interface LibraryMapper extends BaseMapper<LibraryLog> {
}

