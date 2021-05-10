//package uz.pdp.online.m6l2task2datarestpcmarket.projection;
//
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.springframework.data.rest.core.config.Projection;
//import uz.pdp.online.m6l2task2datarestpcmarket.entity.Currency;
//import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;
//import uz.pdp.online.m6l2task2datarestpcmarket.entity.Shopping;
//import uz.pdp.online.m6l2task2datarestpcmarket.entity.User;
//
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@Projection(types = Shopping.class)
//public interface CustomShopping {
//
//    Integer getId();
//
//    User getUser();
//
//
//    List<Product> getProduct();
//
//    Double getPrice();
//
//
//    Currency getCurrency();
//
//     Integer getAmount();
//
//     Double getTotalPrice();
//
//    Timestamp dateWithTime();
//
//
//}
