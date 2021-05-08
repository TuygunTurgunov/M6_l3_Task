package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.User;

@Projection(types = User.class)
public interface CustomUser {

    Integer getId();

    String getEmail();

    String getPhoneNumber();


}