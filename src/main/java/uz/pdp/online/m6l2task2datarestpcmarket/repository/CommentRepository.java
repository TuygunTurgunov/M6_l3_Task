package uz.pdp.online.m6l2task2datarestpcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Comment;
import uz.pdp.online.m6l2task2datarestpcmarket.projection.CustomComment;


@RepositoryRestResource(path = "comment",collectionResourceRel = "list",excerptProjection = CustomComment.class)
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
