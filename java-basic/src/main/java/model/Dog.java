package model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 犬科动物，继承动物抽象类，并实现了奔跑的方法
 *
 * @author BB
 * @create 2020/12/18
 */
public class Dog extends Animal {

    /**
     * 主人
     */
    private String master;

    /**
     * 颜色
     */
    public String color;



    @Override
    protected void call() {
        System.out.println(this.getName() + "的叫声是：汪汪...");
    }

    /**
     * 摇尾巴
     */
    public void tailWagging() {
        System.out.println("摇尾巴...");
    }
}
