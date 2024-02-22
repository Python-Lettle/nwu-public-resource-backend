/**
 * Author: Lettle
 * Date: 2024/2/21
 * Time: 20:14
 * Description: 用于描述信息墙信息审核状态
 */

package cn.lettle.pubresource.util;

public class WallArticleState {
    public static final int PASS = 1;       // 通过审核
    public static final int AUDITTING = 2;  // 审核中
    public static final int FAILED = 3;     // 审核失败
}
