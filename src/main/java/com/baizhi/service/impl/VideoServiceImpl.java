package com.baizhi.service.impl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.entity.Category;
import com.baizhi.entity.Group;
import com.baizhi.entity.User;
import com.baizhi.entity.Video;
import com.baizhi.mapper.VideoMapper;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPO2;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectAllByPage(Integer pageNum, Integer pageSize) {
        Integer i = (pageNum - 1) * pageSize;
        return videoMapper.selectAllByPage(i, pageSize);
    }

    @Override
    public Integer selectCounts() {
        return videoMapper.selectCounts();
    }

    @AddLog("添加视频")
    @DelCahe
    @Override
    public String insert(Video video) {
        String id = UUID.randomUUID().toString();
        video.setId(id);
        video.setUploadTime(new Date());
        Category category = new Category();
        category.setId("2");
        video.setCategory(category);
        Group group = new Group();
        group.setId("1");
        video.setGroup(group);
        User user = new User();
        user.setId("1");
        video.setUser(user);
        videoMapper.insert(video);
        return id;
    }

    @AddLog("删除视频")
    @DelCahe
    @Override
    public void delete(Video video) {
        Video video1 = videoMapper.selectById(video.getId());

        String videoPath = video1.getVideoPath().replace("https://yx-nlg.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = video1.getCoverPath().replace("https://yx-nlg.oss-cn-beijing.aliyuncs.com/", "");

        AliyunOSSUtil.deleteFile("yx-nlg", videoPath);
        AliyunOSSUtil.deleteFile("yx-nlg", coverPath);

        videoMapper.delete(video.getId());
    }

    @Override
    public void uploadVideoAliyun(MultipartFile videoPath, String id, HttpServletRequest request) {
//        上传到阿里云，字节数组形式上传
//        获取文件名
        String filename = videoPath.getOriginalFilename();

//        拼接时间戳
        String newName = new Date().getTime() + "-" + filename;
//        拼接视频文件夹
        String videoName = "video/" + newName;
//        上传视频至阿里云，参数：videoPath：MultipartFile型文件。bucketName:存储空间名。objectName:文件名
        AliyunOSSUtil.uploadFileByte(videoPath, "yx-nlg", videoName);
//          https://yx-nlg.oss-cn-beijing.aliyuncs.com/meinv.jpg
//        截取文件名
        String[] split = newName.split("\\.");
//        拼接图片名
        String coverName = "cover/" + split[0] + ".jgp";
//        截取视频第一帧，参数：buckName：存储空间名，videoName：视频名 文件夹名   coverName：封面名
        AliyunOSSUtil.interceptVideoCover("yx-nlg", videoName, coverName);
//        修改视频信息
        Video video = new Video();
        System.out.println("id:" + id);
        video.setId(id);
        video.setVideoPath("https://yx-nlg.oss-cn-beijing.aliyuncs.com/" + videoName);
        video.setCoverPath("https://yx-nlg.oss-cn-beijing.aliyuncs.com/" + coverName);
        videoMapper.update(video);
    }

    @AddLog("修改视频")
    @DelCahe
    @Override
    public void update(Video video) {
        if (video.getVideoPath() == "") {
            video.setVideoPath(null);
        }
        videoMapper.update(video);
    }

    @Override
    public List<VideoPO> queryByReleaseTime() {
        List<VideoPO> videoPOS = videoMapper.queryByReleaseTime();
        for (VideoPO videoPO : videoPOS) {
//            获取视频id
            String id = videoPO.getId();
//            根据视频id
            videoPO.setLikeCount(20);
        }
        return videoPOS;
    }

    @Override
    public List<VideoPO2> queryByLikeVideoName(String content) {
        String like = "%" + content + "%";
        System.out.println(like);
        return videoMapper.queryByLikeVideoName(like);
    }
}
