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

    public Message (String c, T d) {
        code = c;
        data = d;
    }
}
