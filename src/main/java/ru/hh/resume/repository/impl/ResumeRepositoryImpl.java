package ru.hh.resume.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.resume.model.Resume;
import ru.hh.resume.repository.ResumeRepository;

import java.util.List;

@Repository
public class ResumeRepositoryImpl implements ResumeRepository {

  @Autowired
  private SessionFactory sessionFactory;

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public List<Resume> getAll() {
    return getSession().createQuery("from Resume", Resume.class).list();
  }

  @Override
  public Resume getById(Long id) {
    return getSession().get(Resume.class, id);
  }

  @Override
  public void save(Resume resume) {
    getSession().saveOrUpdate(resume);
  }

  @Override
  public void removeAll() {
    List<Resume> resumes = getAll();
    resumes.forEach(resume -> getSession().delete(resume));
  }

  @Override
  public void saveAll(List<Resume> resumes) {
    resumes.forEach(this::save);
  }
}
