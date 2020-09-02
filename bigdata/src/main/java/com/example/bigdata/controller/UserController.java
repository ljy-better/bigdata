package com.example.bigdata.controller;

import com.example.bigdata.entity.User;
import com.example.bigdata.service.RedisService;
import com.example.bigdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")

public class UserController {
        @Autowired
        private UserService userService;
        @Autowired
        private RedisService redisService;

        @GetMapping("/queryById")
        public String queryById(int id){

                String userStr=redisService.get(id+"");
                if (userStr==null){
                        redisService.set(id+"",userService.queryById(id).toString());
                        return userService.queryById(id).toString();
                }

                return userStr;
        }
        @PostMapping("/save")
        public User saveUser(String name,int age) {
                return userService.save(new User(name,age));
        }


}
