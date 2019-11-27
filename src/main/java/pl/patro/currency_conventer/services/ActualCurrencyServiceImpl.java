package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.patro.currency_conventer.pojo.ConvertValues;
import pl.patro.currency_conventer.entities.ActualCurrency;
import pl.patro.currency_conventer.repositories.ActualCurrencyRepository;

import java.util.Optional;

@Service
public class ActualCurrencyServiceImpl implements ActualCurrencyService {

    private final ActualCurrencyRepository actualCurrencyRepository;

    public ActualCurrencyServiceImpl(ActualCurrencyRepository actualCurrencyRepository) {
        this.actualCurrencyRepository = actualCurrencyRepository;
    }


    @Override
    public ConvertValues getValues(String from, String to) throws NotFoundException {
        ConvertValues convertValues = new ConvertValues();
        if (!from.equals("PLN")) {
            Optional<ActualCurrency> actualCurrencyFrom = actualCurrencyRepository.findByCode(from);
            if (actualCurrencyFrom.isPresent()) {
                convertValues.setFrom(actualCurrencyFrom.get().getMid());
            } else {
                throw new NotFoundException("there is no currency: " + from);
            }
        } else {
            convertValues.setFrom(1);
        }
        if (!to.equals("PLN")) {
            Optional<ActualCurrency> actualCurrencyTo = actualCurrencyRepository.findByCode(to);
            if (actualCurrencyTo.isPresent()) {
                convertValues.setTo(actualCurrencyTo.get().getMid());
            } else {
                throw new NotFoundException("There is no currency: " + to);
            }
        } else {
            convertValues.setTo(1);
        }

        return convertValues;
    }
}







