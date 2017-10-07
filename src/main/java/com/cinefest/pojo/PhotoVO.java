package com.cinefest.pojo;

public class PhotoVO {

  private long id;
  private String encodedContent;

  public PhotoVO(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEncodedContent() {
    return encodedContent;
  }

  public void setEncodedContent(String encodedContent) {
    this.encodedContent = encodedContent;
  }
}
