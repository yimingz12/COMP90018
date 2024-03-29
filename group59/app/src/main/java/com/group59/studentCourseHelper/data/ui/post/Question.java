package com.group59.studentCourseHelper.data.ui.post;

import android.nfc.Tag;

public class Question {
   private String questionId;
    private String questionTitle;
    private String questionDesc;
    private String senderID;
    private String tag;


    public String getSenderName() {
        return senderName;
    }

    private String senderName;

    public Question(){}

    public Question(String questionId, String quesionTitle, String questionDesc,String tag, String senderName,String senderID) {
        this.questionId = questionId;
        this.questionTitle = quesionTitle;
        this.questionDesc = questionDesc;
        this.senderID = senderID;
        this.tag = tag;
        this.senderName=senderName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }
    public String getTag() {
        return tag;
    }
    public String getSenderId() {
        return senderID;
    }

}
