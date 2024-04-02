package com.example.springunity.controller.decoratorTest;

/**
 * @author zx
 * @since 2024-03-20
 */
public class RedShapeDecorator extends ShapeDecorator
{
    public RedShapeDecorator(Shape shapeDecorator)
    {
        super(shapeDecorator);
    }

    @Override
    public void draw()
    {
        decoratoredShape.draw();
        setRedBorder(decoratoredShape);
    }

    public void setRedBorder(Shape decoratordShape) {
        System.out.println("Border Color: red");
    }
}
