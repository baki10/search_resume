package ru.hh.resume.service;

import ru.hh.resume.model.Resume;

import java.util.List;

public interface ResumeService {

  List<Resume> getAll();

  Resume getById(Long id);

  void save(Resume resume);

  void removeAll();

  void refreshDataFromHhRu();

  void saveAll(List<Resume> resumes);
}
