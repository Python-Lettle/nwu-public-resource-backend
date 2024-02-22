/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/22
 * Time: 0:01
 * Description:
 */

package cn.lettle.pubresource.util;

import cn.lettle.pubresource.entity.Classroom;
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

    private int MAX_BUILDING_NUM;

    /**
     * 占用教室函数
     * @param building 楼号
     * @param floor 楼层
     * @param classroom 教室号
     * @return 占用申请成功则返回Classroom对象, 否则null
     */
    public Classroom occupy(int building, int floor, int classroom, Integer request_uid)
    {
        /** 将参数转换成数组下标 **/
        building -= 1;
        floor -= 1;
        classroom -= 1;
        /** 检查参数合法性 **/
        if (!checkClassroomAvailable(building, floor, classroom) || classrooms[building][floor][classroom] == 1) {
            return null;
        }

        Classroom classroom1 = new Classroom(building, floor, classroom, ClassroomState.AUDITTING, request_uid);

        return classroom1;
    }

    /**
     * 释放教室函数
     * @return boolean 释放是否成功
     */
    public boolean release (Classroom classroom)
    {
        if (classroom.getState() != ClassroomState.PASS) {
            return false;
        }
        if (this.classrooms[classroom.getBuilding()][classroom.getFloor()][classroom.getClassroom()] == 1) {
            this.classrooms[classroom.getBuilding()][classroom.getFloor()][classroom.getClassroom()] = 0;
            return true;
        }
        return false;
    }

    /**
     * 审核教室申请通过
     */
    public boolean examine(Classroom classroom)
    {
        // 如果位置是空的 则一定返回true
        if (this.classrooms[classroom.getBuilding()][classroom.getFloor()][classroom.getClassroom()] == 0) {
            // 如果state是批准的 则占用此位置
            if (classroom.getState() == ClassroomState.PASS) {
                this.classrooms[classroom.getBuilding()][classroom.getFloor()][classroom.getClassroom()] = 1;
            }
            return true;
        }

        // 位置被占
        return false;
    }

    /**
     * 检查三个参数是否符合内存中的教室模型
     * @param building 楼号
     * @param floor 层数
     * @param classroom 教室号
     * @return 是否合法
     */
    public boolean checkClassroomAvailable(int building, int floor, int classroom)
    {
        if (building < 0 || building >= MAX_BUILDING_NUM) {
            return false;
        }

        floor -= 1; // 转回数组意义上的下标
        int floor_num = classrooms[building].length;
        if (floor < 0 || floor >= floor_num) {
            return false;
        }

        classroom -= 1; // 转回数组意义上的下标
        int classroom_num = classrooms[building][floor].length;
        if (classroom < 0 || classroom >= classroom_num) {
            return false;
        }
        return true;
    }

    /** 单例模式 **/
    private ClassroomManager() {
        MAX_BUILDING_NUM = classrooms.length;
    }
    private static ClassroomManager instance;
    public static ClassroomManager getInstance() {
        if (instance == null) {
            instance = new ClassroomManager();
        }
        return instance;
    }
}
