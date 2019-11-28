package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;
import pl.patro.currency_conventer.pojo.ConvertValues;
import pl.patro.currency_conventer.entities.ActualCurrency;
import pl.patro.currency_conventer.repositories.ActualCurrencyRepository;

import java.util.Optional;

@Service
public class ActualCurrencyServiceImpl implements ActualCurrencyService {

    private final ActualCurrencyRepository actualCurrencyRepository;
    private final ExchangeRatesTableService exchangeRatesTableService;

    public ActualCurrencyServiceImpl(ActualCurrencyRepository actualCurrencyRepository, ExchangeRatesTableService exchangeRatesTableService) {
        this.actualCurrencyRepository = actualCurrencyRepository;
        this.exchangeRatesTableService = exchangeRatesTableService;
    }


    @Override
    public ConvertValues getValues(String from, String to) throws NotFoundException {
        ConvertValues convertValues = new ConvertValues();
        ExchangeRatesTable exchangeRatesTable = exchangeRatesTableService.getActualTable();
        if (!from.toUpperCase().equals("PLN")) {
            Optional<Double> actualCurrencyFrom = exchangeRatesTable.getRates().stream().filter(x -> x.getCode().equals(from.toUpperCase())).findFirst().map(ActualCurrency::getMid);
            if (actualCurrencyFrom.isPresent()) {
                convertValues.setFrom(actualCurrencyFrom.get());
            } else {
                throw new NotFoundException("there is no currency: " + from);
            }
        } else {
            convertValues.setFrom(1);
        }
        if (!to.toUpperCase().equals("PLN")) {
            Optional<Double> actualCurrencyTo = exchangeRatesTable.getRates().stream().filter(x -> x.getCode().equals(to.toUpperCase())).findFirst().map(ActualCurrency::getMid);
            if (actualCurrencyTo.isPresent()) {
                convertValues.setTo(actualCurrencyTo.get());
            } else {
                throw new NotFoundException("There is no currency: " + to);
            }
        } else {
            convertValues.setTo(1);
        }

        return convertValues;
    }
}







