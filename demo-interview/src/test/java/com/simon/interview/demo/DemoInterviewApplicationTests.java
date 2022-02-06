package com.simon.interview.demo;

import com.simon.interview.demo.designpattern.builder.Director;
import com.simon.interview.demo.designpattern.builder.MyBuilder;
import com.simon.interview.demo.designpattern.factory.Animal;
import com.simon.interview.demo.designpattern.factory.Cat;
import com.simon.interview.demo.designpattern.factory.Dog;
import com.simon.interview.demo.designpattern.factory.MyFactory;
import com.simon.interview.demo.designpattern.inter.AnimalRun;
import com.simon.interview.demo.designpattern.inter.impl.MyDog;
import com.simon.interview.demo.designpattern.observ.Product;
import com.simon.interview.demo.designpattern.observ.ProductNameChangeObserver;
import com.simon.interview.demo.designpattern.prototype.AbstractSpoon;
import com.simon.interview.demo.designpattern.prototype.SoupSpoon;
import com.simon.interview.demo.designpattern.proxy.MyHandler;
import com.simon.interview.demo.designpattern.singleton.StuSingleton;
import com.simon.interview.demo.model.Student;
import com.simon.interview.demo.thread.DemoThread;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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

    @Test
    void getStuSingleton() throws InterruptedException {
        int count = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        StuSingleton stuSingleton = new StuSingleton();
        List<Student> instanceList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    instanceList.add(stuSingleton.getInstance());
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        instanceList.forEach(e->{
            if(e != null){
                System.out.println(e.getName());
            }else{
                //当getInstance不加锁时，就有可能获取到空对象
                System.out.println("存在空对象");
            }
        });

    }

    @Test
    public void createAnimal(){
        MyFactory factory = new MyFactory();
        Animal dog = factory.create(Dog.class);
        dog.run();
        Animal cat = factory.create(Cat.class);
        cat.run();
    }

    @Test
    public void getBuildEntity(){
        MyBuilder builder = new MyBuilder();
        Director director = new Director(builder);
        director.doBuild();
        Student student = builder.getStudent();

        System.out.println(student.getName() + "," + student.getAge());
    }

    @Test
    public void getPrototype() throws CloneNotSupportedException {
        AbstractSpoon spoon = new SoupSpoon();
        SoupSpoon spoon2 = (SoupSpoon) spoon.clone();

        spoon.setSpoonName("spoon 111");
        System.out.println("spoon");

    }

    @Test
    public void doProxy(){
        Object o = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class<?>[]{AnimalRun.class}, // 或RealSubject.class.getInterfaces()
                new MyHandler(new MyDog()));

        AnimalRun animalRun = (AnimalRun)o;
        animalRun.doRun();
    }

    @Test
    public void productNameObservable(){
        Product product = new Product();
        product.addObserver(new ProductNameChangeObserver());

        product.setName("小明");
        System.out.println("exit");
    }
}
