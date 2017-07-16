package ru.hh.resume.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.resume.model.Resume;
import ru.hh.resume.repository.ResumeRepository;
import ru.hh.resume.service.ResumeService;
import ru.hh.resume.parser.HhRuParser;

import java.util.List;

@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

  private ResumeRepository resumeRepository;
  private HhRuParser parser;

  @Autowired
  public void setResumeRepository(ResumeRepository resumeRepository) {
    this.resumeRepository = resumeRepository;
  }

  @Autowired
  public void setParser(HhRuParser parser) {
    this.parser = parser;
  }

  @Override
  public List<Resume> getAll() {
    return resumeRepository.getAll();
  }

  @Override
  public Resume getById(Long id) {
    return resumeRepository.getById(id);
  }

  @Override
  public void save(Resume resume) {
    resumeRepository.save(resume);
  }

  @Override
  public void removeAll() {
    resumeRepository.removeAll();
  }

  @Override
  public void refreshDataFromHhRu() {
    List<Resume> resumes = parser.parse();
    if (resumes.isEmpty()) {
      throw new RuntimeException("No resume found in hh.ru");
    }
    resumeRepository.removeAll();
    saveAll(resumes);
  }

  @Override
  public void saveAll(List<Resume> resumes) {
    resumes.forEach(resume -> {
      resume.getEducationList().forEach(education -> education.setResume(resume));
      resume.getExperienceList().forEach(experience -> experience.setResume(resume));
    });

    resumeRepository.saveAll(resumes);
  }

  @Override
  public List<Resume> findByPosition(String position) {
    return resumeRepository.findByPosition(position);
  }
}
