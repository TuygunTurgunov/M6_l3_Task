package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Category;


@Projection(types = Category.class)
public interface CustomCategory {
    Integer getId();

    Category getParentCategory();

    String getName();





}
