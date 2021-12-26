package com.simon.interview.demo;

import com.simon.interview.demo.thread.DemoThread;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Runner.class)
@SpringBootTest
class DemoInterviewApplicationTests {

    @Test
    void contextLoads() {
        DemoThread thread = new DemoThread();
        thread.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
