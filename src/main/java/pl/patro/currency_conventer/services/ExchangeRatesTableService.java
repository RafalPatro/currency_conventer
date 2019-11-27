package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;

@Service
public interface ExchangeRatesTableService {


    ExchangeRatesTable getActualATableFromWeb() throws NotFoundException;

    ExchangeRatesTable getActualTable() throws NotFoundException;


}
