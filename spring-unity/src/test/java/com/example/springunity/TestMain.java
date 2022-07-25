package com.example.springunity;

import com.example.springunity.impl.TestInterfaceImpl;

import javax.annotation.Resource;

/**
 * @author zx
 * @createTime 2022/7/25 0:01
 */
public class TestMain {
    @Resource
    private static TestInterfaceImpl testInterface;

    @Resource
    private static TestInterfaceImpl testInterface1;

    public static void main(String[] args) {
        testInterface.say1();
        testInterface1.say1();

    }
}
