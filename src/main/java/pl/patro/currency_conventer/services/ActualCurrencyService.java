package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.patro.currency_conventer.pojo.ConvertValues;

@Service
public interface ActualCurrencyService {

    ConvertValues getValues(String from, String to) throws NotFoundException;



}
