package com.simon.interview.demo.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoThread extends Thread{

    @Override
    public void run() {
        while (true){
            log.info("this run thread-name={}",Thread.currentThread().getName());
        }
    }
}
