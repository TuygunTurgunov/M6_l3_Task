package uz.pdp.online.m6l2task2datarestpcmarket.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ShoppingDto {


    @NotNull(message = "user id not be null")
    private Integer userId;

    @NotNull(message = "list of products not be empty")
    private List<Integer> productIdList;

    @NotNull(message = "price of product must have")
    private Double price;

    @NotNull(message = "currency id not be null")
    private Integer currencyId;

    @NotNull(message = "amount not be null")
    private Integer amount;

    @NotNull(message = "total price not be null")
    private Double totalPrice;

}