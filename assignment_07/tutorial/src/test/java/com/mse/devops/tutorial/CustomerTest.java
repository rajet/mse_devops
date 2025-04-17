package com.mse.devops.tutorial;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void shouldReturnCorrectFirstName() {
        Customer customer = new Customer("John", "Doe");
        assertEquals("John", customer.getFirstName());
    }

    @Test
    void shouldReturnCorrectLastName() {
        Customer customer = new Customer("John", "Doe");
        assertEquals("Doe", customer.getLastName());
    }

    @Test
    void shouldHaveNullAttributesWhenUsingDefaultConstructor() {
        Customer customer = new Customer();
        assertNull(customer.getLastName());
        assertNull(customer.getFirstName());
    }
}