package com.springboot.hello.controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;
import com.springboot.hello.domain.dto.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/user/Kyeong")
    public User addAndGet() throws SQLException {
        userDao.add(new User("1", "Kyeongrok", "1234"));
        return userDao.findById("1");
    }

    @PostMapping("/user")
    public User postMember(@RequestBody User user) {
        this.userDao.add(user);
        return userDao.findById(user.getId());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable String id) {
        return ResponseEntity
                .ok()
                .body(userDao.deleteById(id));
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() {
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }
}
