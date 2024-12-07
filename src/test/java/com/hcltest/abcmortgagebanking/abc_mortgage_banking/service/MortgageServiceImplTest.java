package com.hcltest.abcmortgagebanking.abc_mortgage_banking.service;


import com.hcltest.abcmortgagebanking.abc_mortgage_banking.exception.ServiceException;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.model.Mortgage;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.repository.MortgageRepository;
import com.hcltest.abcmortgagebanking.abc_mortgage_banking.service.impl.MortgageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MortgageServiceImplTest {

    @Mock
    private MortgageRepository mortgageRepository;

    @InjectMocks
    private MortgageServiceImpl mortgageServiceImpl;

  @Test
    public void testMortgageSave() throws ServiceException {
      Mortgage mortgage = Mockito.mock(Mortgage.class);
      when(mortgageRepository.save(any(Mortgage.class))).thenReturn(mortgage);
      String response = mortgageServiceImpl.save(mortgage);
      verify(mortgageRepository).save(mortgage);
      Assertions.assertNotNull(response);
  }

    @Test
    public void testMortgageSaveWithException() throws ServiceException {
        Mortgage mortgage = Mockito.mock(Mortgage.class);
        when(mortgageRepository.save(any(Mortgage.class))).thenThrow(RuntimeException.class);
        Throwable exception = Assertions.assertThrows(ServiceException.class, () -> mortgageServiceImpl.save(mortgage));
        Assertions.assertNotNull(exception);
    }
}
