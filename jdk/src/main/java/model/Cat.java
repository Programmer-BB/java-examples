package model;

import lombok.Data;

/**
 * 猫科动物，继承动物抽象类，并实现了奔跑的方法
 *
 * @author BB
 * @create 2020/12/18
 */
@Data
public class Cat extends Animal {


    /**
     * 品种
     */
    private String variety = "普通";

    static {
        System.out.println("Cat 的静态块");
    }

    public Cat() {
        System.out.println("Cat 构造方法");
    }

    @Override
    protected void call() {
        System.out.println(this.getName() + "的叫声是：喵喵...");
    }

    /**
     * 捕老鼠
     */
    public void tryMouse() {
        System.out.println("捕老鼠...");
    }
}
