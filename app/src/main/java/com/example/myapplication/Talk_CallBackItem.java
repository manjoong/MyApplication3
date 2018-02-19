package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 마루소프트 on 2018-01-29.
 */

public class Talk_CallBackItem {
    public Talk_CallBackItem(){
        results = new ArrayList<>();
    }

    private ArrayList<Data> results;

    public ArrayList<Data> getData() {return results;}

    public void setData(ArrayList<Data> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Shop_CallBackItem{" +
                ", results=" + results +
                '}';
    }


    public class Data extends Talk_CallBackItem implements Serializable {
        int num;
        String name;
        String password;
        String content;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Data(int num, String name, String password, String content) {
            this.num = num;
            this.name = name;
            this.password = password;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "num=" + num +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
