package ru.hh.resume.model;

import ru.hh.resume.util.SizeConstants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Education extends BaseEntity{

  private String year;
  private String name;
  private String type;
  private Resume resume;

  @Column(length = SizeConstants.LONG_TEXT)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = SizeConstants.LONG_TEXT)
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

  @ManyToOne(cascade = CascadeType.ALL)
  public Resume getResume() {
    return resume;
  }

  public void setResume(Resume resume) {
    this.resume = resume;
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
