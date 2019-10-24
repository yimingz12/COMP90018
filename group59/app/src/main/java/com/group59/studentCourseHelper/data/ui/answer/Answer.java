package com.group59.studentCourseHelper.data.ui.answer;

public class Answer {
    private String answer;
    private String answerId;
    private String questionId;

    public Answer(String answer, String answerId, String questionId, String ansName) {
        this.answer = answer;
        this.answerId = answerId;
        this.questionId = questionId;
        this.ansName = ansName;
    }

    private String ansName;

    public String getAnswerId() {
        return answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getAnsName() {
        return ansName;
    }

    public Answer(){}



    public String getAnswer() {
        return answer;
    }
}