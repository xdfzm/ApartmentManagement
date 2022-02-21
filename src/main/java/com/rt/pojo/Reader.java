package com.rt.pojo;


public class Reader {

  private int id;
  private String name;
  private String sex;
  private String profession;
  private String phoneNumber;
  private String email;
  private String professionNumber;
  private String address;
  private int leftNumber;
  private int unreturnedNumber;


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getProfession() {
    return profession;
  }

  public void setProfession(String profession) {
    this.profession = profession;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getProfessionNumber() {
    return professionNumber;
  }

  public void setProfessionNumber(String professionNumber) {
    this.professionNumber = professionNumber;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public long getLeftNumber() {
    return leftNumber;
  }

  public void setLeftNumber(int leftNumber) {
    this.leftNumber = leftNumber;
  }


  public long getUnreturnedNumber() {
    return unreturnedNumber;
  }

  public void setUnreturnedNumber(int unreturnedNumber) {
    this.unreturnedNumber = unreturnedNumber;
  }

  @Override
  public String toString() {
    return "Reader{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", profession='" + profession + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", professionNumber='" + professionNumber + '\'' +
            ", address='" + address + '\'' +
            ", leftNumber=" + leftNumber +
            ", unreturnedNumber=" + unreturnedNumber +
            '}';
  }
}
