package cn.lettle.pubresource.mapper;

import cn.lettle.pubresource.entity.Knowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KnowledgeMapper extends BaseMapper<Knowledge> {
}
