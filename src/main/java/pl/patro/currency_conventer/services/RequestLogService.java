package pl.patro.currency_conventer.services;

import org.springframework.stereotype.Service;

@Service
public interface RequestLogService {

    void saveIncoming(String url,String parameters,int statusCode);

    void saveOutgoing(String url,int statusCode);



}
