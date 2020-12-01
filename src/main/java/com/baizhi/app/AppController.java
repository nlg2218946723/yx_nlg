package com.baizhi.app;

import com.baizhi.common.CommonResult;
import com.baizhi.po.CategoryPO;
import com.baizhi.po.VideoPO;
import com.baizhi.po.VideoPO2;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {
    @Resource
    VideoService videoService;

    @Resource
    CategoryService categoryService;

    @RequestMapping("queryByReleaseTime")
    public Object queryByReleaseTime() {
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();

            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }

    }

    @RequestMapping("queryAllCategory")
    public Object queryAllCategory() {
        try {
            List<CategoryPO> categoryPOS = categoryService.queryAllCategory();
            return new CommonResult().success(categoryPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    @RequestMapping("queryByLikeVideoName")
    public Object queryByLikeVideoName(String content) {
        try {
            List<VideoPO2> videoPO2s = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videoPO2s);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }
}
