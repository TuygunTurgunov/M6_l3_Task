package uz.pdp.online.m6l2task2datarestpcmarket.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Value;
import uz.pdp.online.m6l2task2datarestpcmarket.projection.CustomValue;

import java.util.List;

@RepositoryRestResource(path = "value",collectionResourceRel = "list",excerptProjection = CustomValue.class)
public interface ValueRepository extends JpaRepository<Value,Integer> {




}
