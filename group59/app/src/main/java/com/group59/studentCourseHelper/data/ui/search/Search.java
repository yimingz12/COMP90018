package com.group59.studentCourseHelper.data.ui.search;

public class Search {
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

    public Search(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
