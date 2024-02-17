/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 16:02
 * Description: 图书馆类, 描述该图书馆每层座位情况, 预计直接储存在内存中, 用于 WebSocket 实时修改
 */

package cn.lettle.pubresource.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Library {

    /** 三维数组: 楼层 行x 列y
     *  一层楼:
     *   0 0 0 0 0
     *   1 1 1 0 0
     *   0 0 0 0 0
     */
    private int [][][] seats;
    /** 楼层数 **/
    private int floor_num;

    /**
     * Occupy 占座函数
     * @input floor: 楼层数, x:行, y:列
     * @return boolean 占座是否成功
     */
    public boolean occupy (int floor, int x, int y)
    {
        if (this.seats[floor][x][y] == 0) {
            this.seats[floor][x][y] = 1;
            return true;
        }
        return false;
    }

    public boolean checkSeat (int floor, int x, int y)
    {
        return seats[floor][x][y] == 1;
    }
}
