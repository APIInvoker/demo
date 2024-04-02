package com.example.springunity.controller.decoratorTest;

/**
 * @author zx
 * @since 2024-03-20
 */
public class Rectangle implements Shape
{
    @Override
    public void draw()
    {
        System.out.println("draw Rectangle");
    }
}
