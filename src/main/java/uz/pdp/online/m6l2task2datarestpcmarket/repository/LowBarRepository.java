package uz.pdp.online.m6l2task2datarestpcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.LowBar;
import uz.pdp.online.m6l2task2datarestpcmarket.projection.CustomLowBar;


@RepositoryRestResource(path = "lowBar",collectionResourceRel = "list",excerptProjection = CustomLowBar.class)
public interface LowBarRepository extends JpaRepository<LowBar,Integer> {



}
