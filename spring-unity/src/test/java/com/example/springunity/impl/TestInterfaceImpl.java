package com.example.springunity.impl;

import com.example.springunity.TestInterface;
import org.springframework.stereotype.Service;

/**
 * @author zx
 * @createTime 2022/7/24 23:59
 */
@Service
public class TestInterfaceImpl implements TestInterface {
    @Override
    public void say() {
        System.out.println(0);
    }

    @Override
    public void say1() {
        System.out.println(2);
    }
}
