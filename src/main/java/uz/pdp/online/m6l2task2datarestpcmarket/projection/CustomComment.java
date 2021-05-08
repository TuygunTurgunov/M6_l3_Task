package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Comment;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.User;

import java.sql.Timestamp;

@Projection(types = Comment.class)
public interface CustomComment {
    Integer getId();

    User getUser();

    Timestamp getDateWithTime();

    String getText();

    Integer getMark();


}
