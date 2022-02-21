package com.rt.pojo;


public class Detail {

  private long id;
  private String isbn;
  private long readerId;
  private java.sql.Date borrowDate;
  private java.sql.Date dueDate;
  private java.sql.Date returnDate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }


  public long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }


  public java.sql.Date getBorrowDate() {
    return borrowDate;
  }

  public void setBorrowDate(java.sql.Date borrowDate) {
    this.borrowDate = borrowDate;
  }


  public java.sql.Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(java.sql.Date dueDate) {
    this.dueDate = dueDate;
  }


  public java.sql.Date getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(java.sql.Date returnDate) {
    this.returnDate = returnDate;
  }

  @Override
  public String toString() {
    return "Detail{" +
            "id=" + id +
            ", isbn='" + isbn + '\'' +
            ", readerId=" + readerId +
            ", borrowDate=" + borrowDate +
            ", dueDate=" + dueDate +
            ", returnDate=" + returnDate +
            '}';
  }
}
