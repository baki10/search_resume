package ru.hh.resume.repository;

import ru.hh.resume.model.Resume;

import java.util.List;

public interface ResumeRepository {

  List<Resume> getAll();

  Resume getById(Long id);

  void save(Resume resume);

  void removeAll();

  void saveAll(List<Resume> resumes);
}
