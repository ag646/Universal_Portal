package com.dal.universityPortal.controller;

import com.dal.universityPortal.exceptions.ValidationException;
import com.dal.universityPortal.model.University;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserType;
import com.dal.universityPortal.service.AuthenticationServiceImpl;
import com.dal.universityPortal.service.StaffServiceImpl;
import com.dal.universityPortal.service.UniversityProfileService;
import com.dal.universityPortal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static com.dal.universityPortal.constant.ErrorConstant.UNEXPECTED_ERROR;
import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(UNIVERSITY)
public class UniversityProfileController {

    @Autowired
    private UniversityProfileService universityProfileService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StaffServiceImpl staffService;

    @GetMapping(DASHBOARD)
    public String loadUniversityProfile(Model model, HttpServletRequest request) throws SQLException {
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        int id = currentUser.getId();
        University university = universityProfileService.readProfile(id);
        university.setUserId(id);
        model.addAttribute("university", university);
        return "university_profile";
    }

    @PostMapping(SAVE_UNIVERSITY_PROFILE + "/{id}")
    public String saveUniversityProfile(@PathVariable (value = "id") int id,@ModelAttribute("university") University university,Model model) throws SQLException {
        university.setUserId(id);
        University universityCheck = universityProfileService.readProfile(id);
        if (universityCheck.getUniversityName() == null) {
            universityProfileService.saveProfile(university);
        } else{
            universityProfileService.updateProfile(university);
        }
        return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_PROGRAM, id);
    }

    @GetMapping(ADD_STAFF)
    public String addStaffPage(Model model) {
        User staff = new User();
        model.addAttribute("staff", staff);
        return "add_staff";
    }

    @PostMapping(ADD_STAFF)
    public String addStaff(@ModelAttribute User staff, Model model, HttpServletRequest request) {
        User currentUniversity = authenticationService.getCurrentUser(request.getSession());
        model.addAttribute("staff", staff);
        staff.setTypeEnum(UserType.STAFF);
        try {
            userService.addUser(staff);
            staffService.addStaffUniversityMapping(staff, currentUniversity.getId());
            return String.format("redirect:%s%s", UNIVERSITY, DASHBOARD);
        } catch (ValidationException exception) {
            model.addAttribute("errors", exception.getErrors());
        } catch (SQLException exception) {
            model.addAttribute("errors", UNEXPECTED_ERROR);
        }
        return "add_staff";
    }
}