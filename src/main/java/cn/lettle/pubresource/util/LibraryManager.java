/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 16:02
 * Description: 单例模式 图书馆管理类, 描述该图书馆每层座位情况, 预计直接储存在内存中, 用于 WebSocket 实时修改
 */

package cn.lettle.pubresource.util;

import lombok.Getter;

@Getter
public class LibraryManager {

    /** 二维数组: 楼层 座位号 **/
    private int [][] seats = {
            new int[10],
            new int[40],
            new int[20]
    };
    /** 楼层数 **/
    private final int floor_num = 3;

//    public int getFloor_num() {return floor_num;}
//    public int[][][] getSeats() {return seats;}

    /**
     * Occupy 占座函数
     * @param floor 楼层数 从1开始
     * @param pos 位置
     * @return boolean 占座是否成功
     */
    public boolean occupy (int floor, int pos)
    {
        /** 将参数转换成数组下标 **/
        floor -= 1;
        pos -= 1;
        /** 检查参数合法性 **/
        if (!checkPosAvailable(floor, pos) || this.seats[floor][pos] == 1) {
            return false;
        }
        this.seats[floor][pos] = 1;
        // TODO: 增加占座日志，放入数据库中
        return true;
    }

    /**
     * Release 释放座位函数 需要检查座位是否属于该用户
     * @param floor 楼层数 从1开始
     * @param pos 位置
     * @return boolean 是否释放成功
     */
    public boolean release (int floor, int pos)
    {
        /** 将参数转换成数组下标 **/
        floor -= 1;
        pos -= 1;
        /** 检查参数合法性 **/
        if (!checkPosAvailable(floor, pos) || this.seats[floor][pos] == 0) {
            return false;
        }
        this.seats[floor][pos] = 0;
        // TODO: 增加释放座位日志，放入数据库中
        return true;
    }

    /**
     * 检查某个座位是否被占用
     * @param floor 楼层数
     * @param pos 位置
     * @return boolean true为已经被占用 false为未被占用
     */
    public boolean checkSeatOccupied (int floor, int pos)
    {
        floor -= 1;
        pos -= 1;
        if (!checkPosAvailable(floor, pos)) {
            return true;
        }
        return seats[floor][pos] == 1;
    }

    /**
     * 检查座位位置是否合法
     * @return boolean 合法性
     */
    public boolean checkPosAvailable(int floor, int pos)
    {
        if (floor < 0 || floor >= floor_num) {
            return false;
        }
        return true;
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
