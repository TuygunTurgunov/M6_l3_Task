package uz.pdp.online.m6l2task2datarestpcmarket.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.template.AbsEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Product extends AbsEntity {

    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment attachmentPhoto;


    @NotNull(message = "product code not be null")
    @Column(unique = true,nullable = false)
    private String productCode;


    private Integer price;

    @ManyToOne
    private  Currency currency;

    @ManyToOne
    private Measurement measurement;

    private Integer guarantee;

    @ManyToMany
    private List<Value> value;



}
