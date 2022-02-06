package com.example.demojenkins.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/jenkins")
public class JenkinsController {

    private final AtomicInteger flag = new AtomicInteger(1);

    @GetMapping("/test")
    public String test() {
        log.info("test jenkins[{}]" , Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int andAdd = flag.getAndAdd(1);
                    log.info("flag is {}", andAdd);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return "hello jenkins[" + Thread.currentThread().getName() + "]";
    }
}
