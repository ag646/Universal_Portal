package com.dal.universityPortal.controller;

import com.dal.universityPortal.model.Program;
import com.dal.universityPortal.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(UNIVERSITY)
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping(LOAD_PROGRAM + "/{id}")
    public String loadUniversityProgram(@PathVariable(value = "id") int id, Model model) throws SQLException {
        Program program = new Program();
        program.setUniversityId(id);
        model.addAttribute("program", program);
        List<Program> programList=programService.readProgram(id);
        model.addAttribute("programList",programList);
        return "program";
    }

    @PostMapping(SAVE_UNIVERSITY_PROGRAM + "/{id}")
    public String saveUniversityProgram(@PathVariable(value = "id") int id,@ModelAttribute("program") Program program) throws SQLException {
        program.setUniversityId(id);
        programService.saveProgram(program);
        return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_PROGRAM, id);
    }

    @GetMapping(DELETE_PROGRAM + "/{id}/{name}")
    public String deleteUniversityProgram(@PathVariable(value = "id") int id,@PathVariable(value = "name") String name) throws SQLException {
        Program program = new Program(name,id);
        programService.deleteProgram(program);
        return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_PROGRAM, id);
    }
}
