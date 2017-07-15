package ru.hh.resume.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.resume.model.Education;
import ru.hh.resume.model.Experience;
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
    List<Resume> resumes = getSession().createQuery("from Resume", Resume.class).list();
    resumes.forEach(resume -> {
      Query<Education> educationQuery = getSession().createQuery("from Education e where e.resume.id =:id", Education.class);
      educationQuery.setParameter("id", resume.getId());
      resume.setEducationList(educationQuery.list());

      Query<Experience> experienceQuery = getSession().createQuery("from Experience e where e.resume.id =:id", Experience.class);
      experienceQuery.setParameter("id", resume.getId());
      resume.setExperienceList(experienceQuery.list());
    });

    return resumes;
  }

  @Override
  public Resume getById(Long id) {
    return getSession().get(Resume.class, id);
  }

  @Override
  public void save(Resume resume) {
    getSession().save(resume);
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
