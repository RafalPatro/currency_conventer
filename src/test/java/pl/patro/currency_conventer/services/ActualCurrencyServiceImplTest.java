package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.patro.currency_conventer.entities.ActualCurrency;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;
import pl.patro.currency_conventer.pojo.ConvertValues;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ActualCurrencyServiceImplTest {

    @InjectMocks
    ActualCurrencyServiceImpl actualCurrencyService;

    @Mock
    ExchangeRatesTableServiceImpl exchangeRatesTableService;

    void setUp() {
        exchangeRatesTableService = mock(ExchangeRatesTableServiceImpl.class);
        actualCurrencyService = mock(ActualCurrencyServiceImpl.class);
    }
    @Test
    void shouldReturnValidDataWhenValidArguments() throws NotFoundException {
        ExchangeRatesTable exchangeRatesTable = new ExchangeRatesTable();
        exchangeRatesTable.setTable("A");
        exchangeRatesTable.setNo("230/A/NBP/2019");
        exchangeRatesTable.setEffectiveDate(LocalDate.now());

        Set<ActualCurrency> actualCurrencies = new HashSet<>();
        actualCurrencies.add(new ActualCurrency("EUR",10,exchangeRatesTable));
        actualCurrencies.add(new ActualCurrency("USD",20,exchangeRatesTable));
        exchangeRatesTable.setRates(actualCurrencies);


         Mockito.when(exchangeRatesTableService.getActualTable()).thenReturn(exchangeRatesTable);

         ConvertValues convertValues = actualCurrencyService.getValues("USD","EUR");

        assertEquals(10,convertValues.getTo());
        assertEquals(20,convertValues.getFrom());

        convertValues = actualCurrencyService.getValues("PLN","PLN");

        assertEquals(1,convertValues.getTo());
        assertEquals(1,convertValues.getFrom());
    }

    @Test
    void shouldThrowExceptionWhenInvalidArguments() throws NotFoundException {
        ExchangeRatesTable exchangeRatesTable = new ExchangeRatesTable();
        exchangeRatesTable.setTable("A");
        exchangeRatesTable.setNo("230/A/NBP/2019");
        exchangeRatesTable.setEffectiveDate(LocalDate.now());

        Set<ActualCurrency> actualCurrencies = new HashSet<>();
        actualCurrencies.add(new ActualCurrency("EUR",10,exchangeRatesTable));
        actualCurrencies.add(new ActualCurrency("USD",20,exchangeRatesTable));
        exchangeRatesTable.setRates(actualCurrencies);

        Mockito.when(exchangeRatesTableService.getActualTable()).thenReturn(exchangeRatesTable);

       ConvertValues convertValues = null;

       assertThrows(NotFoundException.class, ()->{
           actualCurrencyService.getValues("asd","PLN");
       });
       assertThrows(NotFoundException.class, ()->{
           actualCurrencyService.getValues("EUR","asd");
       });

    }
}