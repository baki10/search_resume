package ru.hh.resume.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.hh.resume.model.Education;
import ru.hh.resume.model.Experience;
import ru.hh.resume.model.Resume;
import ru.hh.resume.util.SizeConstants;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:parser.properties")
public class HhRuParser {

  private static final Logger logger = LoggerFactory.getLogger(HhRuParser.class);

  private static final String HH_RU = "https://hh.ru";
  private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) " +
      "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
  private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

  @Value("${parser.hh.ru.area}")
  private int area;

  @Value("${parser.limited}")
  private boolean limited;


  @Value("${parser.resume_limit}")
  private int resumeLimit;

  public List<Resume> parse() {
    String searchUrl = HH_RU + "/search/resume?area=" + area;
    logger.debug("Start parsing url = {}", searchUrl);

    List<Resume> resumeList = new ArrayList<>();
    try {
      Document document = Jsoup.connect(searchUrl).userAgent(USER_AGENT).get();
      boolean hasNext = true;
      int resumeCount = 1;
      while (hasNext) {
        // get each resume's link
        Element table = document.getElementsByClass("output").first();
        Elements items = table.getElementsByTag("tr");
        for (Element item : items) {
          Element resumeA = item.getElementsByClass("output__name").first();
          String resumeHref = resumeA.attr("href");
          Resume resume = parseResume(HH_RU + resumeHref);
          logger.debug("{}) Resume parsed: {}",resumeCount, resume.getPosition());
          resumeList.add(resume);
          if (limited && resumeCount >= resumeLimit) {
            break;
          }
          resumeCount++;
        }
        if (limited && resumeCount >= resumeLimit) {
          break;
        }

        // check for the "next" link
        Element nextDiv = document.getElementsByClass("b-pager__next").first();
        Elements nextAs = nextDiv.getElementsByTag("a");
        if (nextAs.isEmpty()) {
          hasNext = false;
          continue;
        }
        Element nextA = nextAs.first();
        String nextHref = nextA.attr("href");
        document = Jsoup.connect(HH_RU + nextHref).userAgent(USER_AGENT).get();
      }

      logger.debug("Total resumes = {}", resumeList.size());

    } catch (IOException e) {
      logger.error("Error parsing = {}", e.getMessage());
    }

    logger.debug("Finish parsing url = {}", searchUrl);
    return resumeList;
  }

  private Resume parseResume(String resumeHref) throws IOException {
    Document document = Jsoup.connect(resumeHref).userAgent(USER_AGENT).get();
    Resume resume = new Resume();
    // photo
    Element photoImg = document.getElementsByAttributeValue("data-qa", "resume-photo-image").first();
    resume.setPhotoSrc(photoImg == null ? null : HH_RU + photoImg.attr("src"));
    // gender
    Element genderSpan = document.getElementsByAttributeValue("data-qa", "resume-personal-gender").first();
    resume.setGender(text(genderSpan));
    // birthday
    Element birthdayMeta = document.getElementsByAttributeValue("data-qa", "resume-personal-birthday").first();
    try {
      resume.setBirthday(birthdayMeta == null ? null : dateFormat.parse(birthdayMeta.attr("content")));
    } catch (ParseException e) {
      logger.error("Cannot parse string to date: {}", birthdayMeta.attr("content"));
    }
    //address
    Element addressSpan = document.getElementsByAttributeValue("data-qa", "resume-personal-address").first();
    resume.setAddress(text(addressSpan));
    // position
    Element positionSpan = document.getElementsByAttributeValue("data-qa", "resume-block-title-position").first();
    resume.setPosition(text(positionSpan));
    // salary
    Element salarySpan = document.getElementsByAttributeValue("data-qa", "resume-block-salary").first();
    resume.setSalary(text(salarySpan));
    // experience list
    Element experienceBlock = document.getElementsByAttributeValue("data-qa", "resume-block-experience").first();
    List<Experience> experienceList = new ArrayList<>();
    if (experienceBlock != null) {
      Elements experienceDivs = experienceBlock.getElementsByClass("resume-block-item-gap");
      for (Element experienceDiv : experienceDivs) {
        Experience experience = parseExperience(experienceDiv);
        experienceList.add(experience);
      }
    }
    resume.setExperienceList(experienceList);
    // skills
    Element skillsDiv = document.getElementsByAttributeValue("data-qa", "resume-block-skills").first();
    resume.setAboutMe(html(skillsDiv));
    //education
    List<Education> educationList = new ArrayList<>();
    Element educationBlock = document.getElementsByAttributeValue("data-qa", "resume-block-education").first();
    if (educationBlock != null) {
      Elements educationDivs = educationBlock.getElementsByClass("resume-block-item-gap");
      for (Element educationDiv : educationDivs) {
        Education education = parseEducation(educationDiv);
        educationList.add(education);
      }
    }
    resume.setEducationList(educationList);

    return resume;
  }

  private Experience parseExperience(Element experienceDiv) {
    Experience experience = new Experience();

    Element experienceDurationDiv = experienceDiv.getElementsByClass("resume-block__experience-timeinterval").first();
    experience.setDuration(text(experienceDurationDiv));

    Element companyDiv = experienceDiv.getElementsByAttributeValue("itemprop", "name").first();
    experience.setCompany(text(companyDiv));

    Element addressDiv = experienceDiv.getElementsByAttributeValue("itemprop", "addressLocality").first();
    experience.setAddress(text(addressDiv));

    Element positionDiv = experienceDiv.getElementsByAttributeValue("data-qa", "resume-block-experience-position").first();
    experience.setPosition(text(positionDiv));

    Element descriptionDiv = experienceDiv.getElementsByAttributeValue("data-qa", "resume-block-experience-description").first();
    experience.setDescription(html(descriptionDiv));

    Element experienceUrlA = experienceDiv.getElementsByClass("resume__experience__url").first();
    experience.setUrl(experienceUrlA == null ? null : experienceUrlA.attr("href"));

    return experience;
  }

  private Education parseEducation(Element educationDiv) {
    Education education = new Education();

    Element educationRowDiv = educationDiv.children().first();
    Element yearDiv = educationRowDiv.children().first();
    education.setYear(text(yearDiv));

    Element nameDiv = educationRowDiv.getElementsByAttributeValue("data-qa", "resume-block-education-name").first();
    education.setName(text(nameDiv));

    Element typeDiv = educationRowDiv.getElementsByAttributeValue("data-qa", "resume-block-education-organization").first();
    education.setType(text(typeDiv));

    return education;
  }

  private String text(Element element) {
    return element == null ? null : element.text();
  }

  private String html(Element element) {
    if (element == null) {
      return null;
    }
    String html = element.html();

    return html.length() > SizeConstants.LONG_TEXT ? html.substring(0, SizeConstants.LONG_TEXT) : html;
  }
}
