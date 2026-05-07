package uk.ac.ncl.csc8019.team4.payment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class PaymentTest {

    @Test
    void testInitialStatusIsPending() {
        // Arrange & Act
        Payment payment = new Payment(null, PaymentMethod.CARD);

        // Assert
        assertEquals(PaymentStatus.PENDING, payment.getStatus(), 
            "A new payment should always start in PENDING status.");
        assertNull(payment.getProcessedAt(), "Timestamp should be null before processing.");
    }

    @Test
    void testMarkPaidUpdatesStateCorrectly() {
        // Arrange
        Payment payment = new Payment(null, PaymentMethod.CASH);
        String ref = "TXN-789012";

        // Act
        payment.markPaid(ref);

        // Assert
        assertEquals(PaymentStatus.PAID, payment.getStatus());
        assertEquals(ref, payment.getTransactionReference());
        assertNotNull(payment.getProcessedAt(), "Timestamp must be set upon successful payment.");
        // Check that the timestamp is very recent (within the last second)
        assertTrue(payment.getProcessedAt().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    @Test
    void testMarkFailedUpdatesStateCorrectly() {
        // Arrange
        Payment payment = new Payment(null, PaymentMethod.CARD);

        // Act
        payment.markFailed();

        // Assert
        assertEquals(PaymentStatus.FAILED, payment.getStatus());
        assertNotNull(payment.getProcessedAt());
        assertNull(payment.getTransactionReference(), "Failed payments should not have a transaction reference.");
    }
}
