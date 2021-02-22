package com.bjpowernode.vo;

//保存请求参数值的一个普通类
public class User {
    private String username;
    private Integer userage;

    public User() {
        System.out.println("===User类的无参数构造方法===");
    }

    public User(String username, Integer userage) {
        this.username = username;
        this.userage = userage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserage() {
        return userage;
    }

    public void setUserage(Integer userage) {
        this.userage = userage;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userage=" + userage +
                '}';
    }
}
