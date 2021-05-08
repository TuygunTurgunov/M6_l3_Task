package uz.pdp.online.m6l2task2datarestpcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Measurement;

@Projection(types = Measurement.class)
public interface CustomMeasurement {

    Integer getId();
    String getName();



}
