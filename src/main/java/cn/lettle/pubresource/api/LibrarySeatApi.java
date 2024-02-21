/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 15:57
 * Description: 
 */

package cn.lettle.pubresource.api;

import cn.lettle.pubresource.util.LibraryManager;
import cn.lettle.pubresource.entity.Message;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/library/")
public class LibrarySeatApi {
    private static final LibraryManager libraryManager = LibraryManager.getInstance();

    @PostMapping("/occupy")
    public String occupy (@RequestBody JSONObject body_json)
    {
        int floor = body_json.getIntValue("floor");
        int pos = body_json.getIntValue("pos");
        if (libraryManager.occupy(floor, pos)) {
            log.info(String.format("Floor %d pos = %d 被占座", floor, pos));
            return Message.occupySuccess();
        }
        return Message.occupyFail();
    }

    @PostMapping("/release")
    public String release (@RequestBody JSONObject body_json)
    {
        int floor = body_json.getIntValue("floor");
        int pos = body_json.getIntValue("pos");
        if (libraryManager.release(floor, pos)) {
            log.info(String.format("Floor %d pos = %d 被释放", floor, pos));
            return Message.releaseSuccess();
        }
        return Message.releaseFail();
    }

    @GetMapping("/getSeats")
    public String getSeats ()
    {
        return JSON.toJSONString(libraryManager.getSeats());
    }

}
