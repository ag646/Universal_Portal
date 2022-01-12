package com.dal.universityPortal.controller;

import com.dal.universityPortal.database.ProgramDao;
import com.dal.universityPortal.model.Payment;
import com.dal.universityPortal.model.Program;
import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.AuthenticationService;
import com.dal.universityPortal.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static com.dal.universityPortal.constant.UrlConstant.*;

@Controller
@RequestMapping(STUDENT)
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    AuthenticationService authenticationService;


    @GetMapping(LOAD_PAYMENT + "/{id}")
    public String loadPayment(@PathVariable(value = "id") int id, Model model) throws SQLException {
        Payment payment = new Payment();
        payment.setApplication_id(id);
        List<Program> programList = new ProgramDao().fetchAll();
        payment.setAmount(programList.get(0).getAmount());
        model.addAttribute("payment", payment);
        return "payment";
    }

    @PostMapping(SAVE_PAYMENT + "/{id}")
    public String savePayment(@PathVariable(value = "id") int id, @ModelAttribute("payment") Payment payment, HttpServletRequest request) throws SQLException {
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        payment.setStudent_id(currentUser.getId());
        List<Program> programList = new ProgramDao().fetchAll();
        payment.setAmount(programList.get(0).getAmount());
        payment.setApplication_id(id);
        paymentService.savePayment(payment);
        return String.format("redirect:%s%s", STUDENT, DASHBOARD);
    }
}