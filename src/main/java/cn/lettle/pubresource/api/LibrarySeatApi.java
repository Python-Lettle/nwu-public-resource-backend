/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 15:57
 * Description:
 */

package cn.lettle.pubresource.api;

import cn.lettle.pubresource.entity.LibraryManager;
import cn.lettle.pubresource.entity.Message;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/")
public class LibrarySeatApi {

    private static final LibraryManager libraryManager = LibraryManager.getInstance();

    @PostMapping("/occupy")
    public String occupy (@RequestBody JSONObject body_json)
    {
        int floor = body_json.getIntValue("floor");
        int x = body_json.getIntValue("x");
        int y = body_json.getIntValue("y");
        if (libraryManager.occupy(floor, x, y)) {
            return JSON.toJSONString(new Message<>("occupy", "finish"));
        }

        return JSON.toJSONString(new Message<>("occupy", "fail"));
    }


}
