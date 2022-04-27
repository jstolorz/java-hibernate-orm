package com.bluesoft.javahibernateorm.domain;

public class Chapter {
    private String title;
    private int chapterNumber;

    public Chapter() {
    }

    public Chapter(final String title, final int chapterNumber) {
        this.title = title;
        this.chapterNumber = chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(final int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", chapterNumber=" + chapterNumber +
                '}';
    }
}
