package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video implements Serializable {
    private String id;
    private String title;
    private String brief;
    private String coverPath;
    private String videoPath;
    private Date uploadTime;
    private Integer likeCount;
    private Integer playCount;
    private Category category;
    private User user;
    private Group group;
}
