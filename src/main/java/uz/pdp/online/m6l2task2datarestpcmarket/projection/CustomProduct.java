package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Attachment;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Category;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Measurement;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;

@Projection(types = Product.class)
public interface CustomProduct {

    Integer getId();
    String getName();
    Category getCategory();

    Attachment getAttachmentPhoto();

    String getFeature();

    Measurement getMeasurement();

    String getProductCode();


    Integer getGuarantee();





}
