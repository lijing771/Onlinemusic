package com.example.music.controller;

import com.example.music.mapper.UserMapper;
import com.example.music.model.User;
import com.example.music.tools.Constant;
import com.example.music.tools.ResponseBodyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

@RequestMapping("/login")
    public ResponseBodyMessage<User> login(@RequestParam String username, @RequestParam String password,
                                           HttpServletRequest request){
    User userLogin = new User();
    userLogin.setUsername(username);
    userLogin.setPassword(password);

    User user = userMapper.login(userLogin);
    if (user == null){
        System.out.println("登录失败");
        //状态码为负表示失败
       return new ResponseBodyMessage<>(-1,"登录失败",userLogin);
    }else {
        //登录成功
        HttpSession session = request.getSession(true);
        user.setPassword("");

        session.setAttribute(Constant.USERINFO_SESSION_KEY,user);
        userLogin.setPassword("");

       return new ResponseBodyMessage<>(0,"登录成功",userLogin);
    }
}

}
