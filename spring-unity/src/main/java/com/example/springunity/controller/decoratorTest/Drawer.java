package com.example.springunity.controller.decoratorTest;

/**
 * @author zx
 * @since 2024-03-20
 */
public class Drawer
{
    public static void main(String[] args)
    {
        Circle circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        circle.draw();
        redCircle.draw();
        redRectangle.draw();
    }
}
