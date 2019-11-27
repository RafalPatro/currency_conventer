package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ConverterService {

    double convertCurrencies(String from,String to,double value) throws NotFoundException;




}
