package com.example.myapplication.Message;

import java.io.Serializable;

public class LeaveMessage implements Serializable {
    private int id;
    private String name;
    private String message;
    private String remark;

    public LeaveMessage() {
    }
    public LeaveMessage(int id,String name,String message,String remark) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LeaveMessage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
