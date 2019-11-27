package pl.patro.currency_conventer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.patro.currency_conventer.entities.RequestLog;

@Repository
public interface RequestLogRepository extends CrudRepository<RequestLog, Long> {





}
