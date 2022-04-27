package com.bluesoft.javahibernateorm.domain;

public class Publisher {

    private String code;
    private String name;

    public Publisher() {
    }

    public Publisher(final String code, final String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
