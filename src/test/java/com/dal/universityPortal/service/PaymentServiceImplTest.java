package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @BeforeEach
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void paymentCheck() {
        Payment payment1 = new Payment();
        payment1.setApplication_id(1);
        payment1.setAmount(100);
        payment1.setName("Dhruv");
        payment1.setCardNumber("5425233430109903");
        payment1.setExpiryDate("07/25");
        payment1.setCVV("132");
        payment1.setStudent_id(7);
        Payment payment2 = payment1;
        assertEquals(payment2, payment1);
    }
}
