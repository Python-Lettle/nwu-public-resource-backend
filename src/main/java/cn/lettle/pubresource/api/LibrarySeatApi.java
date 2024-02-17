/**
 * Created with IntelliJ IDEA.
 * Author: Lettle
 * Date: 2024/2/17
 * Time: 15:57
 * Description:
 */

package cn.lettle.pubresource.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library/")
public class LibrarySeatApi {
    
    @PostMapping("/occupy")
    public String occupy ()
    {
        return "";
    }
}
