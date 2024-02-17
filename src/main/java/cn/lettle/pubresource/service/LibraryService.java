package cn.lettle.pubresource.service;

import org.springframework.stereotype.Service;

@Service
public interface LibraryService {
    String getLibraryByFloor(int floor);

    /** 占座 **/
    String occupy(int floor, int x, int y);

}
