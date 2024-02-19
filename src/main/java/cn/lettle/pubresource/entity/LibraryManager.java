/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 16:02
 * Description: 单例模式 图书馆管理类, 描述该图书馆每层座位情况, 预计直接储存在内存中, 用于 WebSocket 实时修改
 */

package cn.lettle.pubresource.entity;

import lombok.Getter;

public class LibraryManager {

    /** 三维数组: 楼层 行x 列y **/
    @Getter private int [][][] seats = {
            // 1F 4x5
        {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0}
        },
            // 2F 5x8
        {
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0}
        },
            // 3F 3x6
        {
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0}
        }
    };
    /** 楼层数 **/
    @Getter private final int floor_num = 3;

    /**
     * Occupy 占座函数
     * @param floor 楼层数
     * @param x 行
     * @param y 列
     * @return boolean 占座是否成功
     */
    public boolean occupy (int floor, int x, int y)
    {
        if (this.seats[floor][x][y] == 1) {
            return false;
        }

        this.seats[floor][x][y] = 1;
        // TODO: 增加占座日志，放入数据库中
        return true;
    }

    /**
     * Release 释放座位函数 需要检查座位是否属于该用户
     * @param floor 楼层数
     * @param x 行
     * @param y 列
     * @return boolean 是否释放成功
     */
    public boolean release (int floor, int x, int y)
    {
        if (this.seats[floor][x][y] == 0) {
            return false;
        }
        this.seats[floor][x][y] = 0;
        // TODO: 增加释放座位日志，放入数据库中
        return true;
    }

    /**
     * 检查某个座位是否被占用
     * @param floor 楼层数
     * @param x 行
     * @param y 列
     * @return boolean true为已经被占用 false为未被占用
     */
    public boolean checkSeatOccupied (int floor, int x, int y)
    {
        return seats[floor][x][y] == 1;
    }

    /** 单例模式 **/
    private LibraryManager() {}
    private static LibraryManager instance;
    public static LibraryManager getInstance() {
        if (instance == null) {
            instance = new LibraryManager();
        }
        return instance;
    }
}
