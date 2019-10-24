package com.group59.studentCourseHelper.data.ui.answer;

public class Answer {
    private String answer;
    private String answerId;

    public Answer(String answer, String answerId, String questionId, String uId, String ansName) {
        this.answer = answer;
        this.answerId = answerId;
        this.questionId = questionId;
        this.uId = uId;
        this.ansName = ansName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getAnsName() {
        return ansName;
    }

    public void setAnsName(String ansName) {
        this.ansName = ansName;
    }

    private String questionId;
    private  String uId;
    private String ansName;


}