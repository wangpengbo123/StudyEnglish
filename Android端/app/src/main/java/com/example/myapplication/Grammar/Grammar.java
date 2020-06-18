package com.example.myapplication.Grammar;

public class Grammar {
    private String name;
    private String content;

    public Grammar(){
    }
    public Grammar(String name,String content){
        this.name = name;
        this.content = content;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }

    @Override
    public String toString() {
        return "Grammar{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
