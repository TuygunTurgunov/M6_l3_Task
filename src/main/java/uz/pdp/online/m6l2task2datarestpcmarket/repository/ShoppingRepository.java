package uz.pdp.online.m6l2task2datarestpcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Shopping;



//@RepositoryRestResource(path = "shopping",collectionResourceRel = "list",excerptProjection = CustomShopping.class)
public interface ShoppingRepository extends JpaRepository<Shopping,Integer> {
}
