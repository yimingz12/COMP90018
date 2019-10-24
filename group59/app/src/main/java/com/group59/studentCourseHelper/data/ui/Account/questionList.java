package com.group59.studentCourseHelper.data.ui.Account;

public class questionList {

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getQid() {
            return qid;
        }

        public void setQid(String qid) {
            this.qid = qid;
        }

        private  String qid;

        public questionList(String title, String content, String qid) {
            this.title = title;
            this.content = content;
            this.qid = qid;
        }




    }

