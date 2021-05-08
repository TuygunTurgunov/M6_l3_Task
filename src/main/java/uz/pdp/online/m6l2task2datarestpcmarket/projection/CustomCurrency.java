package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Currency;

@Projection(types = Currency.class)
public interface CustomCurrency {


    Integer getId();
    String getName();




}
