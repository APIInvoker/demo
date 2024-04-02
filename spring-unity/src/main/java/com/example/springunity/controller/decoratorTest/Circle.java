package com.example.springunity.controller.decoratorTest;

/**
 * @author zx
 * @since 2024-03-20
 */
public class Circle implements Shape
{
    @Override
    public void draw()
    {
        System.out.println("draw circle");
    }
}
