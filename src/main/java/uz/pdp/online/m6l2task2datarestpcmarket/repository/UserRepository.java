package uz.pdp.online.m6l2task2datarestpcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.User;
import uz.pdp.online.m6l2task2datarestpcmarket.projection.CustomUser;


@RepositoryRestResource(path = "user",collectionResourceRel = "list",excerptProjection = CustomUser.class)
public interface UserRepository extends JpaRepository<User,Integer> {
}
