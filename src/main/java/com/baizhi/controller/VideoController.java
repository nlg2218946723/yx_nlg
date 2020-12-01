package com.baizhi.controller;

import com.baizhi.entity.Video;
import com.baizhi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ResponseBody
    @RequestMapping("selectAll")
    public HashMap<String, Object> selectAll(Integer page, Integer rows, HttpServletResponse response, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
//        分页查询结果
        List<Video> videos = videoService.selectAllByPage(page, rows);
        videos.forEach(video -> System.out.println(video));
//        总条数
        Integer integer = videoService.selectCounts();
//        总页数
        Integer totaPage = integer % rows == 0 ? integer / rows : (integer / rows) + 1;
        map.put("page", page);
        map.put("total", totaPage);
        map.put("rows", videos);
        map.put("records", integer);
        return map;

    }

    @RequestMapping("edit")
    @ResponseBody
    public Object edit(Video video, String oper) {
        if (oper.equals("add")) {
            String id = videoService.insert(video);
            return id;

        }
        if (oper.equals("edit")) {
            videoService.update(video);
        }
        if (oper.equals("del")) {
            videoService.delete(video);
        }
        return null;
    }

    @RequestMapping("uploadVideo")
    @ResponseBody
    public void uploadVideo(MultipartFile videoPath, String id, HttpServletRequest request) {
        videoService.uploadVideoAliyun(videoPath, id, request);
    }
}
