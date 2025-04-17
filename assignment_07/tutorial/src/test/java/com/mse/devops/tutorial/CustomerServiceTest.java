package com.mse.devops.tutorial;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(1L, "John", "Doe");
        // Initialize your customer with test data
        // For example: testCustomer.setId(1L); testCustomer.setName("Test Customer");
    }

    @Test
    void findAll() {
        // Arrange
        List<Customer> customerList = Arrays.asList(testCustomer, new Customer());
        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        List<Customer> result = Lists.newArrayList(customerService.findAll());

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(testCustomer, result.getFirst());
        verify(customerRepository).findAll();
    }

    @Test
    void findById() {
        // Arrange
        when(customerRepository.findById(1L)).thenReturn(testCustomer);
        when(customerRepository.findById(2L)).thenReturn(null);

        // Act & Assert - Successful case
        Customer result = customerService.findById(1L);
        assertNotNull(result);
        assertEquals(testCustomer, result);

        // Act & Assert - Not found case
        Customer couldNotFoundResult = customerService.findById(2L);
        assertNull(couldNotFoundResult);

        // Verify interactions
        verify(customerRepository).findById(1L);
        verify(customerRepository).findById(2L);

    }

    @Test
    void create() {
        // Arrange
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        // Act
        Customer result = customerService.create(testCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(testCustomer.getId(), result.getId());
        assertEquals(testCustomer.getFirstName(), result.getFirstName());
        assertEquals(testCustomer.getLastName(), result.getLastName());

        // Verify
        verify(customerRepository).save(testCustomer);
    }

    @Test
    void deleteById() {
        // Arrange
        doNothing().when(customerRepository).deleteById(1L);

        // Act
        customerService.deleteById(1L);

        // Verify
        verify(customerRepository).deleteById(1L);
    }
}
