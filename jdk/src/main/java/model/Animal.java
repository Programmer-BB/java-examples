package model;

import lombok.Data;

/**
 * 动物抽象类
 *
 * @author BB
 * @create
 */
@Data
public abstract class Animal {

    /**
     * 编号
     */
    public int id;

    /**
     * 动物名称
     */
    private String name;

    /**
     * 年龄
     */
    protected int age = 10;

    static {
        System.out.println("Animal的静态块");
    }

    public Animal() {
        System.out.println("Animal的构造方法");
    }

    /**
     * 叫
     */
    protected abstract void call();
}
