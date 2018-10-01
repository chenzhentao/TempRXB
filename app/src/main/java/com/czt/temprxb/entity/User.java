package com.czt.temprxb.entity;

import java.io.Serializable;

/**
 *  Created by {冯中萌} on 2017/4/19
 */

public class User implements Serializable {

    /**
     * retcode : 2000
     * msg : 注册成功
     * data : {"user_id":"8","mobile":"15811163865","nickname":"15811163865","r_token":"iC jZDkBocuaq6XacawkcW7MQz41dao0EWZFJaIYJaqR pGXXnBvReN847ipNCflBrlEqN9RKYZJb9YrgndVTcskqWvq1KxJ"}
     */

    private int retcode;
    private String msg;
    private DataBean data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }


    public static class DataBean implements Serializable {
        /**
         * user_id : 8
         * mobile : 15811163865
         * nickname : 15811163865
         * r_token : iC jZDkBocuaq6XacawkcW7MQz41dao0EWZFJaIYJaqR pGXXnBvReN847ipNCflBrlEqN9RKYZJb9YrgndVTcskqWvq1KxJ
         */

        private String user_id;
        private String mobile;
        private String nickname;
        private String r_token;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getR_token() {
            return r_token;
        }

        public void setR_token(String r_token) {
            this.r_token = r_token;
        }
    }
}
