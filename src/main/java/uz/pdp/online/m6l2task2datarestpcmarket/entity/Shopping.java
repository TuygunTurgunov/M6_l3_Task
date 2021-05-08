package uz.pdp.online.m6l2task2datarestpcmarket.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product>product;

    private Double price;

    @ManyToOne
    private Currency currency;

    private Integer amount;

    private Double totalPrice;

    @CreationTimestamp
    private Timestamp dateWithTime;

}