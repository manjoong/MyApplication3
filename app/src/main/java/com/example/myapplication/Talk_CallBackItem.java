package com.example.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 마루소프트 on 2018-01-29.
 */

public class Talk_CallBackItem {
        public Talk_CallBackItem() {
            results = new ArrayList<>();
        }

//    public  boolean isResponseSuccess() {
//        if (this.getMeta() == null) {
//            return false;
//        } else {
//            if (this.getMeta().getState().equals("OK")) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
//
//
//    private Meta meta;
//
//    public Meta getMeta ()
//    {
//        return meta;
//    }
//
//    public void setMeta (Meta meta)
//    {
//        this.meta = meta;
//    }
//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [meta = "+meta+"]";
//    }
//    public class Meta
//    {
//        private String state;
//
//        private String code;
//
//        private String msg;
//
//        private String type;
//
//        public String getState ()
//        {
//            return state;
//        }
//
//        public void setState (String state)
//        {
//            this.state = state;
//        }
//
//        public String getCode ()
//        {
//            return code;
//        }
//
//        public void setCode (String code)
//        {
//            this.code = code;
//        }
//
//        public String getMsg ()
//        {
//            return msg;
//        }
//
//        public void setMsg (String msg)
//        {
//            this.msg = msg;
//        }
//
//        public String getData() {
//            return type;
//        }
//
//        public void setData(String data) {
//            this.type = type;
//        }
//
//        @Override
//        public String toString() {
//            return "Meta{" +
//                    "state='" + state + '\'' +
//                    ", code='" + code + '\'' +
//                    ", msg='" + msg + '\'' +
//                    ", data='" + type + '\'' +
//                    '}';
//        }
//    }

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

    public class Data extends Talk_CallBackItem implements Serializable {
        int t_no;
        String t_user_id;
        String t_pwd;
        String t_title;
        String t_content;
        String t_write_date;

        public int getT_no() {
            return t_no;
        }

        public void setT_no(int t_no) {
            this.t_no = t_no;
        }

        public String getT_user_id() {
            return t_user_id;
        }

        public void setT_user_id(String t_user_id) {
            this.t_user_id = t_user_id;
        }

        public String getT_pwd() {
            return t_pwd;
        }

        public void setT_pwd(String t_pwd) {
            this.t_pwd = t_pwd;
        }

        public String getT_title() {
            return t_title;
        }

        public void setT_title(String t_title) {
            this.t_title = t_title;
        }

        public String getT_content() {
            return t_content;
        }

        public void setT_content(String t_content) {
            this.t_content = t_content;
        }

        public String getT_write_date() {
            return t_write_date;
        }

        public void setT_write_date(String t_write_date) {
            this.t_write_date = t_write_date;
        }

        public Data(int t_no, String t_user_id, String t_pwd, String t_title, String t_content, String t_write_date) {
            this.t_no = t_no;
            this.t_user_id = t_user_id;
            this.t_pwd = t_pwd;
            this.t_title = t_title;
            this.t_content = t_content;
            this.t_write_date = t_write_date;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "t_no='" + t_no + '\'' +
                    ", t_user_id='" + t_user_id + '\'' +
                    ", t_pwd='" + t_pwd + '\'' +
                    ", t_title='" + t_title + '\'' +
                    ", t_content='" + t_content + '\'' +
                    ", t_write_date='" + t_write_date + '\'' +
                    '}';
        }
    }
}