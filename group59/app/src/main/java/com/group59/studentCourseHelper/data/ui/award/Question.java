package com.group59.studentCourseHelper.data.ui.award;

public class Question {
    public Question(String qTitle, String qDes, String tag, String qId) {
        this.qTitle = qTitle;
        this.qDes = qDes;
        this.tag = tag;
        this.qId = qId;
    }

    String qTitle;

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqDes() {
        return qDes;
    }

    public void setqDes(String qDes) {
        this.qDes = qDes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    String qDes;
    String tag;
    String qId;

}
