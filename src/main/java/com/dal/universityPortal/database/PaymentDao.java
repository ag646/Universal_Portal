package com.dal.universityPortal.database;

import com.dal.universityPortal.model.Payment;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.database.query.DashboardQuery.PAYMENT_FROM_STUDENT_ID;
import static com.dal.universityPortal.database.query.PaymentQuery.*;

@Component
public class PaymentDao implements InsertDao<Payment>, UpdateDao<Payment> {


    public static String fetchStatus(int application_id) throws SQLException {
        List<Map<String, Object>> row;
        try (DBSession dbSession = new DBSession()) {
            row = dbSession.fetch(FETCH_STATUS_APPLICATION_ID, Arrays.asList(application_id));
            return (String) row.get(0).get("status");
        }
    }

    @Override
    public void update(Payment payment) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(UPDATE_STATUS_APPLICATION_ID, Arrays.asList(payment.getApplication_id()));
        }
    }


    @Override
    public void insert(Payment payment) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(INSERT_INTO_PAYMENTS, Arrays.asList(payment.getApplication_id(), payment.getCardNumber(), payment.getAmount(), "Paid", payment.getStudent_id()));
            dbSession.execute(UPDATE_STATUS_PAYMENTS, Arrays.asList(payment.getApplication_id()));
        }
    }

    public List<Payment> fetchPayment(int student_id) throws SQLException {
        List<Map<String, Object>> paymentList;
        List<Payment> payments = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            paymentList = dbSession.fetch(PAYMENT_FROM_STUDENT_ID, Arrays.asList(student_id));
            for (Map<String, Object> mapApplication : paymentList) {
                Payment payment = new Payment();
                payment.setApplication_id(Integer.parseInt(String.valueOf(mapApplication.get("application_id"))));
                payment.setAmount(Integer.parseInt(String.valueOf(mapApplication.get("amount"))));
                payments.add(payment);
            }
        }
        return payments;
    }
}
