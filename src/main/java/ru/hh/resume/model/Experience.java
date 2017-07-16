package ru.hh.resume.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.hh.resume.util.SizeConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Experience extends BaseEntity {

  private String company;
  private String url;
  private String address;
  private String duration;
  private String position;
  private String description;
  private Resume resume;

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Column(length = SizeConstants.LONG_TEXT)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @ManyToOne
  @JoinColumn(name = "resume_id", nullable = false)
  @JsonIgnore
  public Resume getResume() {
    return resume;
  }

  public void setResume(Resume resume) {
    this.resume = resume;
  }

  @Override
  public String toString() {
    return "Experience{" +
        "company='" + company + '\'' +
        ", url='" + url + '\'' +
        ", address='" + address + '\'' +
        ", duration='" + duration + '\'' +
        ", position='" + position + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
