package com.example.music.mapper;

import com.example.music.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User login(User loginUser);
}
