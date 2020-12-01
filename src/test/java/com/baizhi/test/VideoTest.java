package com.baizhi.test;

import com.baizhi.entity.Video;
import com.baizhi.mapper.VideoMapper;
import com.baizhi.po.VideoPO2;
import com.baizhi.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoTest {
    @Autowired
    private VideoMapper videoMapper;

    @Resource
    VideoService videoService;

    @Test
    public void test() {
        Integer integer = videoMapper.selectCounts();
        System.out.println(integer);
    }

    @Test
    public void test2() {
        List<Video> videos = videoMapper.selectAllByPage(0, 1);
        videos.forEach(video -> System.out.println(video));
    }

    @Test
    public void test3() {
        Video video = videoMapper.selectById("1");
        System.out.println(video);
    }

    @Test
    public void test4() {
        List<VideoPO2> a = videoService.queryByLikeVideoName("美女");
        for (VideoPO2 videoPO : a) {
            System.out.println(videoPO);
        }
    }
}

