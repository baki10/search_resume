package ru.hh.resume.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.hh.resume.util.SizeConstants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity
public class Resume extends BaseEntity {

  private String photoSrc;
  private String gender;
  private Date birthday;
  private String address;
  private String position;
  private String salary;
  private String aboutMe;
  private List<Education> educationList;
  private List<Experience> experienceList;

  public String getPhotoSrc() {
    return photoSrc;
  }

  public void setPhotoSrc(String photoSrc) {
    this.photoSrc = photoSrc;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @JsonFormat(pattern = "dd-MM-yyyy")
  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
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

  public String getSalary() {
    return salary;
  }

  public void setSalary(String salary) {
    this.salary = salary;
  }

  @Column(length = SizeConstants.LONG_TEXT)
  public String getAboutMe() {
    return aboutMe;
  }

  public void setAboutMe(String aboutMe) {
    this.aboutMe = aboutMe;
  }

  @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
  public List<Education> getEducationList() {
    return educationList;
  }

  public void setEducationList(List<Education> educationList) {
    this.educationList = educationList;
  }

  @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
  public List<Experience> getExperienceList() {
    return experienceList;
  }

  public void setExperienceList(List<Experience> experienceList) {
    this.experienceList = experienceList;
  }

  @Override
  public String toString() {
    return "Resume{" +
        "photoSrc='" + photoSrc + '\'' +
        ", gender='" + gender + '\'' +
        ", birthday=" + birthday +
        ", address='" + address + '\'' +
        ", position='" + position + '\'' +
        ", salary='" + salary + '\'' +
        ", aboutMe='" + aboutMe + '\'' +
        '}';
  }
}
