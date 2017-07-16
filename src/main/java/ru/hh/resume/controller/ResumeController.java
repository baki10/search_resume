package ru.hh.resume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hh.resume.model.Resume;
import ru.hh.resume.service.ResumeService;

@Controller
@RequestMapping("/resume")
public class ResumeController {

  @Autowired
  private ResumeService resumeService;

  @GetMapping("/{id}")
  public String resume(@PathVariable Long id, Model model){
    Resume resume = resumeService.getById(id);
    model.addAttribute("resume", resume);

    return "resume-details";
  }
}
