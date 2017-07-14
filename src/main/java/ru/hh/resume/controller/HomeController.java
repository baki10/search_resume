package ru.hh.resume.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hh.resume.model.Resume;
import ru.hh.resume.util.HhRuParser;

import java.util.List;

@Controller
public class HomeController {

  private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

  @GetMapping("/")
  public String home(Model model) {
    logger.debug("home() ---- start");

    model.addAttribute("message", "HELLO WORLD!!!(from controller)");

    logger.debug("home() ---- end");
    List<Resume> resumeList = new HhRuParser().parse();

    return "home";
  }
}
