package com.Murashev.pavel.controllers;

import com.Murashev.pavel.domain.entity.*;
import com.Murashev.pavel.domain.repo.StudentRepo;
import com.Murashev.pavel.domain.service.FirstnameService;
import com.Murashev.pavel.domain.service.LastnameService;
import com.Murashev.pavel.domain.service.SecondnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private LastnameService lastnameService;

    @Autowired
    private FirstnameService firstnameService;

    @Autowired
    private SecondnameService secondnameService;
    @Value( "${upload.path}" )
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping("/main")
    public String main(Model model){
        Iterable<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "main";
    }
    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam int groupp,
            Model model,
            @RequestParam("file")MultipartFile file
    ) throws IOException {
        String resultFileName = null;
        if (file!= null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File( uploadPath );
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo( new File( uploadDir + "\\" + resultFileName ) );
        }
        String[] names = text.split(" ");
        firstnameService.saveIntoFirstname(new Firstname(names[1]));
        Firstname firstname = firstnameService.findFirstnameByName(names[1]);
        secondnameService.saveIntoSecondname(new Secondname(names[2]));
        Secondname secondname = secondnameService.findSecondnameByName(names[2]);
        lastnameService.saveIntoLastname(new Lastname(names[0]));
        Lastname lastname = lastnameService.findLastnameByName(names[0]);
        final Student student = new Student(firstname,secondname,lastname, groupp, user, resultFileName);
        studentRepo.save( student );
        Iterable<Student> students = studentRepo.findAll();
        model.addAttribute("students", students);
        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model model){
        Iterable<Student> students;
        if (filter != null && !filter.isEmpty()) {
            int flrt = Integer.parseInt( filter );
            students = studentRepo.findByGroupp( flrt );
        } else {
            students = studentRepo.findAll();
        }
        model.addAttribute("students", students);
        return "main";
    }
}
