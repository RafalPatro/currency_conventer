package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import pl.patro.currency_conventer.entities.ActualCurrency;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;
import pl.patro.currency_conventer.pojo.ConvertValues;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ConverterServiceImplTest {

    @InjectMocks
    ConverterServiceImpl converterService;

    @Mock
    ActualCurrencyServiceImpl actualCurrencyService;
    void setUp() {
        converterService = mock(ConverterServiceImpl.class);
        actualCurrencyService = mock(ActualCurrencyServiceImpl.class);
    }

    @Test
    void shouldReturnCorrectResult() throws NotFoundException {
        Mockito.when(actualCurrencyService.getValues(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(new ConvertValues(10,20));
        assertEquals(5,converterService.convertCurrencies("USD","EUR",10));
    }
    @Test
    void shouldThrowArithmeticException() throws NotFoundException {
        Mockito.when(actualCurrencyService.getValues(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(new ConvertValues(10,0));
        assertThrows(ArithmeticException.class, ()->{
            converterService.convertCurrencies("pln","pln",9);
        });
    }


}