package com.example.onlinestore_number1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "total")
    private Double total;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
