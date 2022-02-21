package com.rt.pojo.vo;

public class DetailDTOVo {

    private String bookName;
    private String name;
    private String returned;

    private Integer page = 1;

    @Override
    public String toString() {
        return "DetailDTOVo{" +
                "bookName='" + bookName + '\'' +
                ", name='" + name + '\'' +
                ", returned='" + returned + '\'' +
                ", page=" + page +
                '}';
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturned() {
        return returned;
    }

    public void setReturned(String returned) {
        this.returned = returned;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
