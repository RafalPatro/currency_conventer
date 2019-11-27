package pl.patro.currency_conventer.services;

import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.patro.currency_conventer.entities.ActualCurrency;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;
import pl.patro.currency_conventer.repositories.ExchangeRatesTableRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;


@Service
public class ExchangeRatesTableServiceImpl implements ExchangeRatesTableService {
    private final ExchangeRatesTableRepository exchangeRatesTableRepository;
    private final RequestLogService requestLogService;

    public ExchangeRatesTableServiceImpl(ExchangeRatesTableRepository exchangeRatesTableRepository, RequestLogService requestLogService) {
        this.exchangeRatesTableRepository = exchangeRatesTableRepository;
        this.requestLogService = requestLogService;
    }


    @Override
    public ExchangeRatesTable getActualATableFromWeb() throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/tables/a/?format=json";
        ResponseEntity<ExchangeRatesTable[]> response =
                restTemplate.getForEntity(url, ExchangeRatesTable[].class);

        requestLogService.saveOutgoing(url,response.getStatusCodeValue());
        if(response.getStatusCodeValue()==200) {
            ExchangeRatesTable ex = Objects.requireNonNull(response.getBody())[0];
            for (ActualCurrency ac : ex.getRates()
            ) {
                ac.setExchangeRatesTable(ex);
            }
            return exchangeRatesTableRepository.save(ex);
        }else throw new NotFoundException("Invalid server response");
    }

    @Override
    public ExchangeRatesTable getActualTable() throws NotFoundException {
        Optional<ExchangeRatesTable> optionalExchangeRatesTableService;
        if(LocalTime.now().isBefore(LocalTime.of(11,45,0))){
          optionalExchangeRatesTableService =  exchangeRatesTableRepository.findByEffectiveDate(LocalDate.now().minusDays(1));
        }else{
          optionalExchangeRatesTableService = exchangeRatesTableRepository.findByEffectiveDate(LocalDate.now());
        }
        if(optionalExchangeRatesTableService.isEmpty()){
            return getActualATableFromWeb();
        }else return optionalExchangeRatesTableService.get();
    }



}
