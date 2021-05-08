package uz.pdp.online.m6l2task2datarestpcmarket.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "email not be null")
    @Column(unique = true,nullable = false)
    private String email;

    @NotNull(message = "phone not be null")
    @Column(unique = true,nullable = false)
    private String phoneNumber;

}