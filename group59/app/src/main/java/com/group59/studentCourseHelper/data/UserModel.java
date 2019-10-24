package com.group59.studentCourseHelper.data;

public class UserModel {


        private String name;
        private String password;
        private String email;
        private String uid;

        public String getName() {
                return name;
        }

        public String getEmail() {
                return email;
        }

        public String getPassword() {
                return password;
        }


        public String getUid() {
                return uid;
        }

        public UserModel(String name, String email, String password, String uid) {

                this.name = name;
                this.password = password;
                this.email = email;

                this.uid = uid;
        }

}
