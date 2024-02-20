/**
 * Author: Lettle
 * Date: 2024/2/19
 * Time: 15:00
 * Description: 通用返回模板
 */

package cn.lettle.pubresource.entity;

import com.alibaba.fastjson2.JSON;
import lombok.*;

@Data
@NoArgsConstructor
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
    public static String loginSuccess() {return JSON.toJSONString(new Message<>("register","success"));}
    public static String loginFail() {return JSON.toJSONString(new Message<>("register","fail"));}
    public static String publishSuccess() {return JSON.toJSONString(new Message<>("register","success"));}
    public static String publishFail() {return JSON.toJSONString(new Message<>("register","fail"));}
    public static String checkSuccess() {return JSON.toJSONString(new Message<>("register","success"));}
    public static String checkFail() {return JSON.toJSONString(new Message<>("register","fail"));}
    public static String retailSuccess() {return JSON.toJSONString(new Message<>("register","success"));}
    public static String retailFail() {return JSON.toJSONString(new Message<>("register","fail"));}
    public Message (String c, T d) {
        code = c;
        data = d;
    }
}
