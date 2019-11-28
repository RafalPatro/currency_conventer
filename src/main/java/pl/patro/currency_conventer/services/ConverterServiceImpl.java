package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.patro.currency_conventer.pojo.ConvertValues;

@Service
public class ConverterServiceImpl implements ConverterService {

    private final ActualCurrencyService actualCurrencyService;

    public ConverterServiceImpl(ActualCurrencyService actualCurrencyService) {
        this.actualCurrencyService = actualCurrencyService;
    }

    @Override
    public double convertCurrencies(String from, String to, double amount) throws NotFoundException {
       ConvertValues convertValues = actualCurrencyService.getValues(from,to);
       if(convertValues.getTo()==0){
           throw new ArithmeticException("Value of "+ to +" =0!");
       }
       return amount * convertValues.getFrom()/convertValues.getTo();
    }
}
