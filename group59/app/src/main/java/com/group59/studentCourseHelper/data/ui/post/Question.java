package com.group59.studentCourseHelper.data.ui.post;

public class Question {
   private String questionId;
    private String quesionTitle;
    private String questionDesc;
    private String senderID;
    public Question(){}

    public Question(String questionId, String quesionTitle, String questionDesc, String senderID) {
        this.questionId = questionId;
        this.quesionTitle = quesionTitle;
        this.questionDesc = questionDesc;
        this.senderID = senderID;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuesionTitle() {
        return quesionTitle;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public String getSenderMail() {
        return senderID;
    }
}
