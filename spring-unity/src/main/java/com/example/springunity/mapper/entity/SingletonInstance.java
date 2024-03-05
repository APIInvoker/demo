package com.example.springunity.mapper.entity;

/**
 * @author zx
 * @since 2024-02-29
 */
public class SingletonInstance
{
    private static volatile SingletonInstance instance;

    private SingletonInstance() {}

    // 饿汉式单例模式 double-checked
    @SuppressWarnings("all")
    public static SingletonInstance getInstance()
    {
        if (instance == null) {
            synchronized (UserInfo.class) {
                if (instance == null) {
                    instance = new SingletonInstance();
                }
            }
        }
        return instance;
    }
}
