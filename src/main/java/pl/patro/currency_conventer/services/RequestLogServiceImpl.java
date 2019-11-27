package pl.patro.currency_conventer.services;

import org.springframework.stereotype.Service;
import pl.patro.currency_conventer.entities.RequestLog;
import pl.patro.currency_conventer.repositories.RequestLogRepository;

@Service
public class RequestLogServiceImpl implements RequestLogService {

    private final RequestLogRepository requestLogRepository;

    public RequestLogServiceImpl(RequestLogRepository requestLogRepository) {
        this.requestLogRepository = requestLogRepository;
    }

    @Override
    public void saveIncoming(String url, String parameters, int statusCode) {
        requestLogRepository.save(new RequestLog("INCOMONG",url,parameters,statusCode));
    }

    @Override
    public void saveOutgoing(String url, int statusCode) {
        requestLogRepository.save(new RequestLog("OUTGOING",url,null,statusCode));

    }
}
