package com.baizhi.mapper;

import com.baizhi.entity.Admin;

public interface AdminMapper {
    Admin selectByUsername(String username);
}
