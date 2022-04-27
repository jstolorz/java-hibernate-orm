package com.bluesoft.javahibernateorm.domain;

import java.util.List;

public class Book {

    private String isbn;
    private String name;
    private Publisher publisher;
    private List<Chapter> chapters;

    public Book() {
    }

    public Book(final String isbn, final String name, final Publisher publisher) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(final Publisher publisher) {
        this.publisher = publisher;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(final List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", publisher=" + publisher +
                ", chapters=" + chapters +
                '}';
    }
}
