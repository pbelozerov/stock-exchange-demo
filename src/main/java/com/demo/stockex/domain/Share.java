package com.demo.stockex.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Getter
@Setter
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "companyName")
    private String name;

    private Integer amount;

    @JsonProperty(value = "latestPrice")
    private Double price;

    @ManyToOne
    private User user;

    public double getTotalCost() {
        return amount * price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Share share = (Share) o;

        return Objects.equals(symbol, share.symbol);
    }

    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }
}
