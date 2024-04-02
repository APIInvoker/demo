package com.example.springunity.controller.decoratorTest;

/**
 * @author zx
 * @since 2024-03-20
 */
public abstract class ShapeDecorator implements Shape
{
    protected Shape decoratoredShape;

    public ShapeDecorator(Shape decoratoredShape)
    {
        this.decoratoredShape = decoratoredShape;
    }

    // public void draw() {
    //     decoratoredShape.draw();
    // }
}
