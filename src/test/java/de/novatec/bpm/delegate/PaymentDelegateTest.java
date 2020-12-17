package de.novatec.bpm.delegate;

import de.novatec.bpm.exception.PaymentException;
import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.PaymentService;
import de.novatec.bpm.variable.ProcessVariables;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class PaymentDelegateTest {

    @Mock
    private DelegateExecution execution;

    @Mock
    private PaymentService paymentService;

    private PaymentDelegate delegate;

    @BeforeEach
    void setUp() {
        delegate = new PaymentDelegate(paymentService);
        Reservation reservation = new Reservation();
        UserAccount userAccount = new UserAccount("thrillhouse", "thrillhouse@camunda-cinema.de", "DE123456", "VOBAXXX");
        reservation.setUserAccount(userAccount);
        given(execution.getVariable(ProcessVariables.RESERVATION.getName())).willReturn(reservation);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_issueMoney_happy_path() {
        Assertions.assertDoesNotThrow(() -> delegate.issueMoney(execution));
    }

    @Test
    void test_issueMoney_payment_error() {
        doThrow(new PaymentException("error")).when(paymentService).issueMoney(anyInt(), anyString(), any());
        BpmnError error = Assertions.assertThrows(BpmnError.class, () -> delegate.issueMoney(execution));
        Assertions.assertEquals("B002", error.getErrorCode());
    }

    @Test
    void test_giveMoneyBack_happy_path() {
        Assertions.assertDoesNotThrow(() -> delegate.giveMoneyBack(execution));
    }
}