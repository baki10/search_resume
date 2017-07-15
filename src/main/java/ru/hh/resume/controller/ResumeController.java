package ru.hh.resume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hh.resume.service.ResumeService;

@Controller
@RequestMapping("/resume")
public class ResumeController {

  private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);

  @Autowired
  private ResumeService resumeService;

  @GetMapping("/refresh")
  public String refreshDb(Model model) {
    resumeService.refreshDataFromHhRu();
    model.addAttribute("resumes", resumeService.getAll());
    return "resume-list";
  }
}
