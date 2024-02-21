/**
 * Author: Lettle
 * Date: 2024/2/19
 * Time: 15:00
 * Description: 通用返回模板
 */

package cn.lettle.pubresource.entity;

import com.alibaba.fastjson2.JSON;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<T>
{
    public String code;
    public T data;

    public static String occupySuccess() {return JSON.toJSONString(new Message<>("occupy","success"));}
    public static String occupyFail() {return JSON.toJSONString(new Message<>("occupy","fail"));}
    public static String releaseSuccess() {return JSON.toJSONString(new Message<>("release","success"));}
    public static String releaseFail() {return JSON.toJSONString(new Message<>("release","fail"));}
    public static String registerSuccess() {return JSON.toJSONString(new Message<>("register","success"));}
    public static String registerFail() {return JSON.toJSONString(new Message<>("register","fail"));}
    public static String loginSuccess() {return JSON.toJSONString(new Message<>("login","success"));}
    public static String loginFail() {return JSON.toJSONString(new Message<>("login","fail"));}
    public static String publishSuccess() {return JSON.toJSONString(new Message<>("publish","success"));}
    public static String publishFail() {return JSON.toJSONString(new Message<>("publish","fail"));}
    public static String examineSuccess() {return JSON.toJSONString(new Message<>("examine","success"));}
    public static String examineFail() {return JSON.toJSONString(new Message<>("examine","fail"));}
    public static String getArticles(List<WallArticle> articles) {
        return JSON.toJSONString(
                new Message<>("articles", JSON.toJSONString(articles))
        );
    }
}
