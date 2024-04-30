package com.softwaretesting.testing.validator;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class CustomerValidatorTest {

    @Test
    public void testValidate404ObjectPresentNoExceptionThrown() {
        CustomerValidator validator = new CustomerValidator();
        Optional<String> object = Optional.of("someObject");
        String label = "User-Name";
        String value = "Jacob";

        assertDoesNotThrow(() -> validator.validate404(object, label, value));
    }

    @Test
    public void testValidate404ObjectNotPresentResponseStatusExceptionThrown() {
        CustomerValidator validator = new CustomerValidator();
        Optional<String> object = Optional.empty();
        String label = "User-Name";
        String value = "Jacob";

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> validator.validate404(object, label, value));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("java.util.Optional with User-Name'Jacob' does not exist.", exception.getReason());
    }
}