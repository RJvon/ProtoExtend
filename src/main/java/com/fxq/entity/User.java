package com.fxq.entity;

import com.fxq.annotation.IsNullMethod;
import com.fxq.annotation.MyData;
import com.fxq.annotation.PutStringMethod;

@PutStringMethod("")

public class User {

    private volatile java.lang.Object ask1;
    private volatile java.lang.Object askLot1;
    private volatile java.lang.Object askChannel1;

    public String getAskChannel1() {
        return (String) askChannel1;
    }

    public double getAsk1() {
        return (double) ask1;
    }

    public int getAskLot1() {
        return (int) askLot1;
    }

    public boolean hasAskLot1() {
        return true;
    }

    public boolean hasAskChannel1() {
        return true;
    }

    public boolean hasAsk1() {
        return true;
    }

    public User setAsk1(double value) {
        this.ask1 = value;
        return this;
    }

    public User setAskLot1(int value) {
        this.ask1 = value;
        return this;
    }

    public User setAskChannel1(String value) {
        this.ask1 = value;
        return this;
    }


}
