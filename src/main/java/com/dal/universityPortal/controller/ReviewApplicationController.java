package com.dal.universityPortal.controller;

import com.dal.universityPortal.constant.EmailConstant;
import com.dal.universityPortal.constant.ErrorConstant;
import com.dal.universityPortal.constant.ReviewApplicationConstant;
import com.dal.universityPortal.model.Application;
import com.dal.universityPortal.model.Email;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.AuthenticationService;
import com.dal.universityPortal.service.EmailServiceImpl;
import com.dal.universityPortal.service.ReviewApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(UNIVERSITY)
public class ReviewApplicationController {

    @Autowired
    private ReviewApplicationService reviewApplicationService;


    @Autowired
    AuthenticationService authenticationService;

    @GetMapping(LOAD_LIST_APPLICATIONS)
    public String loadApplicationList(Model model) throws SQLException {
        List<Application> applicationList=reviewApplicationService.readList();
        model.addAttribute("listApplication",applicationList);
        return "list_of_application";
    }

    @GetMapping(LOAD_APPLICATION_PAGE + "/{id}")
    public String loadApplication(@PathVariable(value = "id") int id,Model model) throws SQLException {
        Application application= reviewApplicationService.oneApplication(id);
        model.addAttribute("app",application);
        return "review_application";
    }

    @GetMapping(LOCK_APPLICATION + "/{id}")
    public String lockApplication(@PathVariable(value = "id") int id,@ModelAttribute("application") Application application, RedirectAttributes redirectAttributes, HttpServletRequest request) throws SQLException {
        Application application1= reviewApplicationService.oneApplication(id);
        application.setApplication_id(application1.getApplication_id());
        application.setStatus("In-process");
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        application.setProcessed_by(currentUser.getId());
        if(!application1.getStatus().equals("In-process")){
            reviewApplicationService.saveReviewApplication(application);
            redirectAttributes.addFlashAttribute("error", ErrorConstant.APPLICATION_LOCK);
            return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_APPLICATION_PAGE, id);
        }
        else{
            redirectAttributes.addFlashAttribute("error", ErrorConstant.APPLICATION_LOCK);
            return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_APPLICATION_PAGE, id);
        }
    }

    @GetMapping(REJECT_APPLICATION + "/{id}")
    public String rejectApplication(@PathVariable(value = "id") int id,@ModelAttribute("application") Application application, RedirectAttributes redirectAttributes) throws SQLException, MessagingException {
        Application application1= reviewApplicationService.oneApplication(id);
        application.setApplication_id(application1.getApplication_id());
        application.setStatus(ReviewApplicationConstant.STATUS_REJECT);
        application.setProcessed_by(application1.getProcessed_by());
        if(!application1.getStatus().equals("New")){
            reviewApplicationService.saveReviewApplication(application);
            EmailServiceImpl emailService = new EmailServiceImpl();
            Email email = emailService.addDetailsWithAttachment(EmailConstant.MAIL_ID,EmailConstant.DECISION,
                    EmailConstant.DECISION_REJECT,EmailConstant.REJECT_LETTER);
            emailService.sendMailWithAttachment(email);
            return String.format("redirect:%s%s", UNIVERSITY, LOAD_LIST_APPLICATIONS);
        }
        else{
            redirectAttributes.addFlashAttribute("error1", ErrorConstant.APPLICATION_LOCK_ERROR);
            return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_APPLICATION_PAGE, id);
        }
    }

    @PostMapping(SAVE_REVIEW_APPLICATION + "/{id}")
    public String saveReviewApplication(@PathVariable(value = "id") int id,@ModelAttribute("application") Application application, RedirectAttributes redirectAttributes) throws SQLException, MessagingException {
        Application application1= reviewApplicationService.oneApplication(id);
        application.setApplication_id(application1.getApplication_id());
        application.setStatus(ReviewApplicationConstant.STATUS_ACCEPT);
        application.setProcessed_by(application1.getProcessed_by());
        if(!application1.getStatus().equals("New")){
            reviewApplicationService.saveReviewApplication(application);
            EmailServiceImpl emailService = new EmailServiceImpl();
            Email email = emailService.addDetailsWithAttachment(EmailConstant.MAIL_ID,EmailConstant.DECISION,
                    EmailConstant.DECISION_ACCEPT,EmailConstant.ACCEPT_LETTER);
            emailService.sendMailWithAttachment(email);
            return String.format("redirect:%s%s", UNIVERSITY, LOAD_LIST_APPLICATIONS);
        }
        else{
            redirectAttributes.addFlashAttribute("error1", ErrorConstant.APPLICATION_LOCK_ERROR);
            return String.format("redirect:%s%s/%s", UNIVERSITY, LOAD_APPLICATION_PAGE, id);
        }

    }
}
