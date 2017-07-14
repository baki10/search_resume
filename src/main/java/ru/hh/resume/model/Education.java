package ru.hh.resume.model;

public class Education {

  private String year;
  private String name;
  private String type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  @Override
  public String toString() {
    return "Education{" +
        "year='" + year + '\'' +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        '}';
  }
}
