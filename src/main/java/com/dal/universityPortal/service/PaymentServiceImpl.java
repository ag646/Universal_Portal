package com.dal.universityPortal.service;

import com.dal.universityPortal.database.PaymentDao;
import com.dal.universityPortal.model.Payment;
import com.dal.universityPortal.validator.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDAO;

    @Override
    public void savePayment(Payment payment) {
        try {
            String status = "";
            CardValidator cardValidator = new CardValidator();
            if (cardValidator.isValid(payment.getCardNumber())) {
                status = PaymentDao.fetchStatus(payment.getApplication_id());
                if (status.equals("Pending")) {
                    paymentDAO.update(payment);
                    paymentDAO.insert(payment);
                }
            }
        } catch (Exception e) {
        }
    }
}
