package com.rt.pojo;


public class Book {

  private String isbn;
  private String bookName;
  private String author;
  private String type;
  private double price;
  private String publisher;
  private int publishYear;
  private int totalNumber;
  private int lentNumber;


  @Override
  public String toString() {
    return "Book{" +
            "isbn='" + isbn + '\'' +
            ", bookName='" + bookName + '\'' +
            ", author='" + author + '\'' +
            ", type='" + type + '\'' +
            ", price=" + price +
            ", publisher='" + publisher + '\'' +
            ", publishYear=" + publishYear +
            ", totalNumber=" + totalNumber +
            ", lentNumber=" + lentNumber +
            '}';
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getPublishYear() {
    return publishYear;
  }

  public void setPublishYear(int publishYear) {
    this.publishYear = publishYear;
  }

  public int getTotalNumber() {
    return totalNumber;
  }

  public void setTotalNumber(int totalNumber) {
    this.totalNumber = totalNumber;
  }

  public int getLentNumber() {
    return lentNumber;
  }

  public void setLentNumber(int lentNumber) {
    this.lentNumber = lentNumber;
  }
}
