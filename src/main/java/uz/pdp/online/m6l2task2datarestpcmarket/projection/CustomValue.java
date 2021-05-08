package uz.pdp.online.m6l2task2datarestpcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Feature;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Projection(types = Value.class)
public interface CustomValue {

    Integer getId();

    String getVal();

    List<Feature> getFeature();


}
