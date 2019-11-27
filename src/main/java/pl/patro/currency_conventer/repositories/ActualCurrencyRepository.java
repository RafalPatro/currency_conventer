package pl.patro.currency_conventer.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.patro.currency_conventer.entities.ActualCurrency;

import java.util.Optional;

@Repository
public interface ActualCurrencyRepository extends CrudRepository<ActualCurrency,Long> {


    Optional<ActualCurrency> findByCode(String code);
}
