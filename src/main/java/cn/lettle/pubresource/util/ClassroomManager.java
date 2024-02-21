/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/22
 * Time: 0:01
 * Description:
 */

package cn.lettle.pubresource.util;

import lombok.Getter;

@Getter
public class ClassroomManager {
    /** 三维数组: 楼号、楼层、教室号 **/
    private int[][][] classrooms = {
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
            {new int[10], new int[10], new int[10], new int[10], new int[10]},
    };

    /**
     * 占用教室函数
     * @param building_num 楼号
     * @param floor 楼层
     * @param classroom 教室号
     * @return
     */
    public boolean occupy(int building_num, int floor, int classroom)
    {

    }
}
