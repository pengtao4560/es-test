package com.atguigu.cloudsleuth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pt
 * @createdate 2022/01/12 0012
 * @desc
 */
@RestController
@Slf4j
public class SleuthController {
    @RequestMapping("/sleuth")
    public String home() {
        log.info("Handling home");
        return "Hello World";
    }
}
