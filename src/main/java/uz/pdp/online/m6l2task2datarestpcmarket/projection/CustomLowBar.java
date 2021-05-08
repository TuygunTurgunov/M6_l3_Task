package uz.pdp.online.m6l2task2datarestpcmarket.projection;


import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Attachment;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Comment;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.LowBar;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Projection(types = LowBar.class)
public interface CustomLowBar {
    Integer getId();

    Product getProduct();

    String getDescription();

    Attachment getAttachmentVideo();

    List<Comment> getComment();


}
