package uz.pdp.online.m6l2task2datarestpcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.AttachmentContent;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {

    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);


}
