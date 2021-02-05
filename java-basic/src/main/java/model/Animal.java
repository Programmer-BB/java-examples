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
    protected int age;

    /**
     * 叫
     */
    protected abstract void call();
}
