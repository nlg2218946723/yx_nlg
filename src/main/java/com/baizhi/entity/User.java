package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "名字")
    private String nickName;
    @Excel(name = "电话")
    private Integer phone;
    @Excel(name = "头像")
    private String picImg;
    @Excel(name = "简介")
    private String brief;
    @Excel(name = "学分")
    private Double score;
    @Excel(name = "注册日期")
    private LocalDate createDate;
    @Excel(name = "状态")
    private Integer status;
    @Excel(name = "城市")
    private String city;
}
