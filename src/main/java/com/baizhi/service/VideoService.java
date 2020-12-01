package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPO2;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {
    //查询全部一级类别
    List<Video> selectAllByPage(Integer pageNum, Integer pageSize);

    //    查询一级类别总条数
    Integer selectCounts();

    //  添加ship
    String insert(Video video);

    //    删除视频
    void delete(Video video);

    //    将视频上传到阿里云
    void uploadVideoAliyun(MultipartFile videoPath, String id, HttpServletRequest request);

    //    修改
    void update(Video video);

    //  前台查询全部
    List<VideoPO> queryByReleaseTime();

    //    前台根据like查询
    List<VideoPO2> queryByLikeVideoName(String content);

}
