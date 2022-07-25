package com.example.springunity;

/**
 * @author zx
 * @createTime 2022/7/24
 */
public interface TestInterface {
    void say();

    default void say1() {
        System.out.println(1);
    }
}
