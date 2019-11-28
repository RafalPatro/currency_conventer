package pl.patro.currency_conventer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "actual_currencies", schema = "currencies")
public class ActualCurrency {

    @Id
    @Column(name = "currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String currency;

    @Column
    private String code;

    @Column
    private double mid;

    @Column
    private double bid;

    @Column
    private double ask;


    @ManyToOne(targetEntity = ExchangeRatesTable.class)
    @JoinColumn(name = "rate_id")
    @JsonIgnore
    private ExchangeRatesTable exchangeRatesTable;

    public ActualCurrency() {
    }

    public ActualCurrency(String code, double mid, ExchangeRatesTable exchangeRatesTable) {
        this.code = code;
        this.mid = mid;
        this.exchangeRatesTable = exchangeRatesTable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public ExchangeRatesTable getExchangeRatesTable() {
        return exchangeRatesTable;
    }

    public void setExchangeRatesTable(ExchangeRatesTable exchangeRatesTable) {
        this.exchangeRatesTable = exchangeRatesTable;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }
}
