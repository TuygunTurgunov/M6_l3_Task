package uz.pdp.online.m6l2task2datarestpcmarket.payload;

import lombok.Data;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.*;


import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ProductDto {

    @NotNull(message = "product name not be empty")
    private String name;

    private Boolean active=true;



    private Integer category;


    private Integer attachmentId;


    @NotNull(message = "product code not be null")
    private String productCode;


    private Integer price;


    private Integer currencyId;


    private Integer measurementId;

    private Integer guarantee;


    private List<Integer> valueId;






}
