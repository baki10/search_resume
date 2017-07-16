package ru.hh.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.resume.model.Resume;
import ru.hh.resume.service.ResumeService;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

  @Autowired
  private ResumeService resumeService;

  @GetMapping("/refresh")
  public List<Resume> refresh() {
    resumeService.refreshDataFromHhRu();
    return resumeService.getAll();
  }

  @GetMapping("/all")
  public List<Resume> resumes() {
    return resumeService.getAll();
  }

  @GetMapping("/search")
  public List<Resume> search(@RequestParam String position) {
    return resumeService.findByPosition(position);
  }
}
