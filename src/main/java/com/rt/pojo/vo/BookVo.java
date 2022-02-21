package com.rt.pojo.vo;

public class BookVo {
    private String bookName;
    private String type;
    private String author;

    private Integer page = 1;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "BookVo{" +
                "bookName='" + bookName + '\'' +
                ", type='" + type + '\'' +
                ", author='" + author + '\'' +
                ", page=" + page +
                '}';
    }
}
