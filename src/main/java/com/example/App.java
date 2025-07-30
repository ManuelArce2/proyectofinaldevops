package com.example;
import io.javalin.Javalin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static Javalin createApp() {
        return Javalin.create().get("/hello", ctx -> ctx.result("Hello World"));
    }

    public static void main(String[] args) {
        createApp().start(7000);
}
}