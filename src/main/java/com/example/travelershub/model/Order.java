package com.example.travelershub.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Apartment> apartments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
    private BigDecimal amount;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public Order(Long id, List<Apartment> apartments, User client, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.apartments = apartments;
        this.client = client;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.amount = apartments.stream().map(Apartment::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
