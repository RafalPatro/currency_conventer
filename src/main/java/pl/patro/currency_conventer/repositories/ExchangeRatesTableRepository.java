package pl.patro.currency_conventer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.patro.currency_conventer.entities.ExchangeRatesTable;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRatesTableRepository extends CrudRepository<ExchangeRatesTable, String> {

    Optional<ExchangeRatesTable> findByEffectiveDate(LocalDate localDate);


}
