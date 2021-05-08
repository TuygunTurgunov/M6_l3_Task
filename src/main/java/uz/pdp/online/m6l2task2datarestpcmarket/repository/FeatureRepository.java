package uz.pdp.online.m6l2task2datarestpcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Feature;
import uz.pdp.online.m6l2task2datarestpcmarket.projection.CustomFeature;


@RepositoryRestResource(path = "feature",collectionResourceRel = "list",excerptProjection = CustomFeature.class)
public interface FeatureRepository extends JpaRepository<Feature,Integer> {



}
