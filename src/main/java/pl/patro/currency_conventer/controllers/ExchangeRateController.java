package pl.patro.currency_conventer.controllers;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;
import pl.patro.currency_conventer.services.ConverterService;
import pl.patro.currency_conventer.services.ExchangeRatesTableService;
import pl.patro.currency_conventer.services.RequestLogService;

@Controller
@RequestMapping("/currencies")
@RestController
public class ExchangeRateController {

    private final ExchangeRatesTableService exchangeRatesTableService;

    private final RequestLogService requestLogService;

    private final ConverterService converterService;

    public ExchangeRateController(ExchangeRatesTableService exchangeRatesTableService, RequestLogService requestLogService, ConverterService converterService) {
        this.exchangeRatesTableService = exchangeRatesTableService;
        this.requestLogService = requestLogService;
        this.converterService = converterService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getRates(){
        ExchangeRatesTable exchangeRatesTable;
        try {
            exchangeRatesTable = exchangeRatesTableService.getActualTable();
            requestLogService.saveIncoming("currencies/list",null,200);
            return new ResponseEntity<>(exchangeRatesTable, HttpStatus.OK);
        }catch(NotFoundException e){

            requestLogService.saveIncoming("currencies/list",null,500);
            return new ResponseEntity<>("No actual data available", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/convert/{from}/{to}/{amount}")
    public ResponseEntity<?> convert(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable double amount){
        double result;
        try {
            result = converterService.convertCurrencies(from, to, amount);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        requestLogService.saveIncoming("/convert/{from}/{to}/{amount}", from + " " + to + " " + amount,200);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }




}
