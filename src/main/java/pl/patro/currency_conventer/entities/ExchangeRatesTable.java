package pl.patro.currency_conventer.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "exchange_rates", schema = "currencies")
public class ExchangeRatesTable {

    @Column(name = "table_version")
    private String table;

    @Column(name = "rate_id")
    @Id
    private String no;

    @Column
    private LocalDate tradingDate;

    @Column
    private LocalDate effectiveDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exchangeRatesTable", cascade = CascadeType.ALL, targetEntity = ActualCurrency.class)
    private Set<ActualCurrency> rates;

    public ExchangeRatesTable() {
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Set<ActualCurrency> getRates() {
        return rates;
    }

    public void setRates(Set<ActualCurrency> rates) {
        this.rates = rates;
    }
}
