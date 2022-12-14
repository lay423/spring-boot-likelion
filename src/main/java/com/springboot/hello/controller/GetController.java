package com.springboot.hello.controller;

import com.springboot.hello.domain.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
@Slf4j
public class GetController {

    @RequestMapping(name = "/hello", method = RequestMethod.GET)
    public String hello() {
        log.info("hello로 요청이 들어왔습니다.");
        return "HelloWorld";
    }

    @GetMapping("/name")
    public String getName() {
        log.info("getName으로 요청이 들어왔습니다.");
        return "Junha";
    }

    @GetMapping("/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        log.info("getVaribale1으로 요청이 들어왔습니다.");
        return variable;
    }

    @GetMapping("/variable2/{variable}")
    public String getVariable2(@PathVariable String variable) {
        return variable;
    }

    @GetMapping("/request1")
    public String getVariable3(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String organization) {
        return String.format("%s %s %s", name, email, organization);
    }

    @GetMapping("/request2")
    public String getVariable4(@RequestParam
                               Map<String, String> param) {
        param.entrySet().forEach(
                (map) -> {
                    System.out.printf("key:%s value:%s\n", map.getKey(), map.getValue());
                }
        );
        return "호출완료";
    }

    @GetMapping("/request3")
    public String getRequestParam(MemberDto memberDto) {
        return memberDto.toString();
    }
}
