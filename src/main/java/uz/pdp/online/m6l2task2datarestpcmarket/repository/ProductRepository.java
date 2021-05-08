package uz.pdp.online.m6l2task2datarestpcmarket.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;
import uz.pdp.online.m6l2task2datarestpcmarket.projection.CustomProduct;

import javax.validation.constraints.NotNull;
import java.util.List;


@RepositoryRestResource(path = "product",collectionResourceRel = "list",excerptProjection = CustomProduct.class)
public interface ProductRepository extends JpaRepository<Product,Integer> {

boolean existsByNameAndCategoryId(@NotNull(message = "name not be null") String name, Integer category_id);

boolean existsByNameAndIdNot(@NotNull(message = "name not be null") String name, Integer id);
boolean existsByNameAndCategoryIdNotAndIdNot(@NotNull(message = "name not be null") String name, Integer category_id, Integer id);


    @Query(value = "select *\n" +
            "from product p\n" +
            "         join product_value pv on p.id = pv.product_id\n" +
            "         join value v on pv.value_id = v.id\n" +
            "         join feature f on v.feature_id = f.id\n" +
            "where f.id in :feature \n" +
            "  and v.id in :valuen",nativeQuery = true)
    Page<Product>getProductByValue(List<Integer>feature, List<Integer>valuen, Pageable pageable);





}
