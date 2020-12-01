package com.baizhi.mapper;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPO2;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    List<Video> selectAllByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    Integer selectCounts();

    void delete(String id);

    void update(Video video);

    void insert(Video video);

    Video selectById(String id);

    List<VideoPO> queryByReleaseTime();

    List<VideoPO2> queryByLikeVideoName(String content);
}
