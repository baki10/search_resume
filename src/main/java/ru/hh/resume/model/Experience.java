package ru.hh.resume.model;

public class Experience {

  private String company;
  private String url;
  private String address;
  private String duration;
  private String position;
  private String description;

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
