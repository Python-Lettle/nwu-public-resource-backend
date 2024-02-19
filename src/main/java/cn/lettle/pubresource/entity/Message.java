/**
 * Author: Lettle
 * Date: 2024/2/19
 * Time: 15:00
 * Description: 通用返回模板
 */

package cn.lettle.pubresource.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message<T>
{
    private String code;
    private T data;

    public Message (String c, T d) {
        code = c;
        data = d;
    }
}
