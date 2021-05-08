package uz.pdp.online.m6l2task2datarestpcmarket.entity.template;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@MappedSuperclass
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "name not be null")
    @Column(unique = true,nullable = false)
    private String name;

    private Boolean active=true;




}
