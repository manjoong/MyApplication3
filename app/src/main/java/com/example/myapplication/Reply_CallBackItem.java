package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 마루소프트 on 2018-01-29.
 */

public class Reply_CallBackItem {
    public Reply_CallBackItem() {
        results = new ArrayList<>();
    }


    private ArrayList<Data> results;  //여기서 results를 맞춰줘야해

    public ArrayList<Data> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Talk_CallBackItem{" +
                "results=" + results +
                '}';
    }

    public static class Data implements Serializable {
        int r_no;
        int r_t_no;
        String r_user_id;
        String r_content;
        String r_write_date;

        public int getR_no() {
            return r_no;
        }

        public void setR_no(int r_no) {
            this.r_no = r_no;
        }

        public int getR_t_no() {
            return r_t_no;
        }

        public void setR_t_no(int r_t_no) {
            this.r_t_no = r_t_no;
        }

        public String getR_user_id() {
            return r_user_id;
        }

        public void setR_user_id(String r_user_id) {
            this.r_user_id = r_user_id;
        }

        public String getR_content() {
            return r_content;
        }

        public void setR_content(String r_content) {
            this.r_content = r_content;
        }

        public String getR_write_date() {
            return r_write_date;
        }

        public void setR_write_date(String r_write_date) {
            this.r_write_date = r_write_date;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "r_no=" + r_no +
                    ", r_t_no=" + r_t_no +
                    ", r_user_id='" + r_user_id + '\'' +
                    ", r_content='" + r_content + '\'' +
                    ", r_write_date='" + r_write_date + '\'' +
                    '}';
        }
    }
}