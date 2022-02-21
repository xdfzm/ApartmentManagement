package com.rt.pojo.vo;

public class ReaderVo {

    private String name;
    private String profession;
    private Integer page = 1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ReaderVo{" +
                "name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", page=" + page +
                '}';
    }
}
