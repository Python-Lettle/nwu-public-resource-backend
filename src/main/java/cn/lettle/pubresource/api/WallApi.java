package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.Message;
import cn.lettle.pubresource.entity.WallComment;
import cn.lettle.pubresource.entity.WallArticle;
import cn.lettle.pubresource.mapper.WallCommentMapper;
import cn.lettle.pubresource.mapper.WallArticleMapper;
import cn.lettle.pubresource.util.WallArticleState;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Repository
@CrossOrigin
@RequestMapping("/wall/")
@RestController
public class WallApi {
    @Autowired
    public WallArticleMapper wallArticleMapper;

    @Autowired
    public WallCommentMapper wallCommentMapper;

    /**
     * 发表文章
     * @param body_json
     * @return
     */
    @PostMapping("/publish_article")
    public String publish_article(@RequestBody JSONObject body_json) {
        // TODO: 检查用户登录状态
        /** 获取参数 **/
        Integer uid = body_json.getIntValue("uid");        // 发布者id
        String text = body_json.getString("text");      // 发布内容

        /** 发表文章 **/
        WallArticle article = new WallArticle();
        article.setArticle_uid(uid);
        article.setArticle_text(text);
        article.setArticle_state(WallArticleState.AUDITTING);
        wallArticleMapper.insert(article);

        log.info(String.format("%s 发表一篇文章", uid));
        return Message.publishSuccess();
    }

    @PostMapping("/publish_comment")
    public String publish_comment (@RequestBody JSONObject body_json) {
        // TODO: 检查用户登录状态
        /** 获取参数 **/
        Integer uid = body_json.getIntValue("uid");         // 发布者id
        Integer article_id = body_json.getIntValue("article_id");  // 评论的文章id
        String text = body_json.getString("text");          // 发布内容

        /** 发表评论 **/
        WallComment comment = new WallComment();
        comment.setComment_uid(uid);
        comment.setComment_text(text);
        comment.setPublish_id(article_id);
        comment.setComment_state(WallArticleState.AUDITTING);
        wallCommentMapper.insert(comment);

        log.info(String.format("%s 对文章(%d)发表一篇评论", uid, article_id));
        return Message.publishSuccess();
    }

    /**
     * 审核文章
     * @param body_json
     * @return
     */
    @PostMapping("/examine")
    public String examine(@RequestBody JSONObject body_json) {
        // TODO: 检查用户权限
        /** 获取参数 **/
        Integer id = body_json.getIntValue("id");        // 文章id
        int type = body_json.getIntValue("type");       // 文章类型
        int state = body_json.getIntValue("state");     // 修改为状态

        /** 根据类型 从不同数据库查询文章 **/
        if (type == 1) {            // 审核文章
            WallArticle article = wallArticleMapper.selectById(id);
            article.setArticle_state(state);
            wallArticleMapper.updateById(article);
        } else {                    // 审核评论
            WallComment comment = wallCommentMapper.selectById(id);
            comment.setComment_state(state);
            wallCommentMapper.updateById(comment);
        }

        return Message.examineSuccess();
    }

    /**
     * 获取文章
     * @param body_json
     * @return
     */
    @GetMapping("/getArticle")
    public String getArticle(@RequestBody JSONObject body_json){
        /** 获取参数 **/
        Integer num = body_json.getIntValue("num");        // 获取 num 篇文章
        /** 构建 wrapper 获取最后num篇文章 **/
        QueryWrapper<WallArticle> wrapper = new QueryWrapper<>();       // new wrapper 对象
        wrapper.last(String.format("limit %d", num));                   // wrapper.last() 和 limit num 限制查询最后 num 条
        List<WallArticle> articles = wallArticleMapper.selectList(wrapper);

        log.info(String.format("获取 %d 篇文章", articles.size()));
        return Message.getArticles(articles);
    }
}

