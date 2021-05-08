package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Feature;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;


import java.util.List;

@Projection(types = Feature.class)
public interface CustomFeature {

    Integer getId();

    String getName();

    List<Product> getProduct();


}
