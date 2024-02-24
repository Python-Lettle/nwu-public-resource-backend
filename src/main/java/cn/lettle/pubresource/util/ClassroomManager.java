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

import java.util.HashMap;
import java.util.Map;

@Getter
public class ClassroomManager {
    /** 三维数组: 楼号、楼层、教室号 **/
//    private int[][][] classrooms = {
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//            {new int[10], new int[10], new int[10], new int[10], new int[10]},
//    };

    // building floor classroom
    private String[][][] classrooms = {
            {   // 1 教学楼
                    {"1101", "1102", "1103", "1104", "1105", "1106", "1107", "1108", "1109", "1110", "1j101"},
                    {"1201", "1202", "1203", "1204", "1205", "1206", "1207", "1208", "1209", "1210", "1j201"},
                    {"1301", "1302", "1303", "1304", "1305", "1306", "1307", "1308", "1309", "1310", "1j301"},
                    {"1401", "1402", "1403", "1404", "1405", "1406", "1407", "1408", "1409", "1410"},
                    {"1501", "1502", "1503", "1504", "1505", "1506", "1507", "1508", "1509", "1510"},
            },
            {   // 2 教学楼
                    {"2101", "2102", "2103", "2104", "2105", "2106", "2107", "2108", "2109", "2110", "2j101"},
                    {"2201", "2202", "2203", "2204", "2205", "2206", "2207", "2208", "2209", "2210", "2j201"},
                    {"2301", "2302", "2303", "2304", "2305", "2306", "2307", "2308", "2309", "2310", "2j301"},
                    {"2401", "2402", "2403", "2404", "2405", "2406", "2407", "2408", "2409", "2410"},
                    {"2501", "2502", "2503", "2504", "2505", "2506", "2507", "2508", "2509", "2510"},
            },
            {   // 3 教学楼
                    {"3101", "3102", "3103", "3104", "3105", "3106", "3107", "3108", "3109", "3110", "3j101"},
                    {"3201", "3202", "3203", "3204", "3205", "3206", "3207", "3208", "3209", "3210", "3j201"},
                    {"3301", "3302", "3303", "3304", "3305", "3306", "3307", "3308", "3309", "3310", "3j301"},
                    {"3401", "3402", "3403", "3404", "3405", "3406", "3407", "3408", "3409", "3410"},
                    {"3501", "3502", "3503", "3504", "3505", "3506", "3507", "3508", "3509", "3510"},
            },
            {   // 4 教学楼
                    {"4101", "4102", "4103", "4104", "4105", "4106", "4107", "4108", "4109", "4110", "4j401"},
                    {"4201", "4202", "4203", "4204", "4205", "4206", "4207", "4208", "4209", "4210", "4j201"},
                    {"4301", "4302", "4303", "4304", "4305", "4306", "4307", "4308", "4309", "4310", "4j301"},
                    {"4401", "4402", "4403", "4404", "4405", "4406", "4407", "4408", "4409", "4410"},
                    {"4501", "4502", "4503", "4504", "4505", "4506", "4507", "4508", "4509", "4510"},
            },
    };

    private static Map<String, Integer> occupy_log = new HashMap<>();

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
        if (!checkClassroomAvailable(building, floor, classroom) || occupy_log.containsKey(getClassroomName(building,floor,classroom))) {
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

    private String getClassroomName(int building, int floor, int room) {
        return this.classrooms[building][floor][room];
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
