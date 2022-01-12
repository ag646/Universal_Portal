package com.dal.universityPortal.controller;

import com.dal.universityPortal.model.University;
import com.dal.universityPortal.service.SearchUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

import static com.dal.universityPortal.constant.ErrorConstant.UNIVERSITY_NOT_FOUND_ERROR;
import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(STUDENT)
public class SearchUniversityController {

    @Autowired
    private SearchUniversityService searchUniversityService;

    @GetMapping(SEARCH_UNIVERSITY)
    public String searchUniversity(Model model) {
        University university = new University();
        model.addAttribute("universityDetail", university);
        return "search_university";
    }

    @PostMapping(GET_UNIVERSITY_DETAILS)
    public String getUniversityDetails(@ModelAttribute("universityDetail") University university,
                                       Model model, RedirectAttributes redirectAttributes) throws SQLException {
        University universityDetails = searchUniversityService.getUniversityDetails(university);
        model.addAttribute("universityDetail", universityDetails);
        model.addAttribute("programList", searchUniversityService.getProgramDetails(universityDetails.getUserId()));
        if (universityDetails.getUniversityName().length() > 0) {
            model.addAttribute("universityDetail", universityDetails);
            return "university_details";
        } else {
            redirectAttributes.addFlashAttribute("error", UNIVERSITY_NOT_FOUND_ERROR);
            return String.format("redirect:%s%s", STUDENT, SEARCH_UNIVERSITY);
        }
    }
}
