package com.group59.studentCourseHelper.data;

public class UserModel {


        private String name;
        private String password;
        private String email;
        private String uid;
        //public int pwdresetFlag=0;

        public String getUserName() {
                return name;
        }
        public void setUserName(String name) {
                this.name = name;
        }

        public String getUserPwd() {
                return password;
        }
        public void setUserPwd(String password) {
                this.password = password;
        }

        public String getUserId() {
                return email;
        }
        public void setUserId(String email) {
                this.email = email;
        }


        public String getUID() {
                return uid;
        }
        public void setUID(String uid) {
                this.uid = uid;
        }


        public UserModel(String name, String password, String email,  String uid) {

                this.name = name;
                this.password = password;
                this.email = email;

                this.uid = uid;
        }

}
