package com.example.springunity.impl;

import com.example.springunity.TestInterface;
import org.springframework.stereotype.Service;

/**
 * @author zx
 * @createTime 2022/7/25 0:00
 */
@Service
public class TestInterfaceImpl1 implements TestInterface {
    @Override
    public void say() {
        System.out.println(0);
    }

    @Override
    public void say1() {
        System.out.println(2);
    }
}
