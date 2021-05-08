package uz.pdp.online.m6l2task2datarestpcmarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Attachment;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.AttachmentContent;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.AttachmentContentRepository;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {


    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    private static  final   String  uploadDirectory="downloads";

    @PostMapping("/upload")
    public  String uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file!=null) {
            // file haqida ma'lumot olish
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment =new Attachment();
            attachment.setFileOriginalName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);

            //file ni byte[] saqalash
            AttachmentContent attachmentContent=new AttachmentContent();
            attachmentContent.setMainContent(file.getBytes());
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return "file saved. id:"+savedAttachment.getId();
        }

        return "  xatolik";

    }


    @GetMapping("/show/{id}")
    public Attachment showOne(@PathVariable Integer id){

        return attachmentRepository.findById(id).orElseGet(null);

    }


//    @PostMapping("/uploadSystem")
//    public  String uploadFileSystem(MultipartHttpServletRequest request) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());
//        if (file!=null){
//            Attachment  attachment =new Attachment();
//            String originalFilename = file.getOriginalFilename();
//            attachment.setFileOriginalName(originalFilename);
//            attachment.setSize(file.getSize());
//            attachment.setContentType(file.getContentType());
//            //Uyga.borish.jpg
//            String[] split = originalFilename.split("\\.");
//
//            //dsdasdas-s2332423-323234df-d355er.jpg
//            String name = UUID.randomUUID().toString()+"."+split[split.length-1];
//            attachment.setName(name);
//
//            attachmentRepository.save(attachment);
//
//            //File byte[] larinini saqlash  (projectni ichida saqladik)
//            Path path= Paths.get(uploadDirectory+"/"+name);
//            Files.copy(file.getInputStream(),path);
//            return "File saved  . Id: "+attachment.getId();
//        }
//        return " File not saved";
//    }
//

//    @GetMapping("/getFile/{id}")
//    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
//
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()){
//            Attachment attachment = optionalAttachment.get();
//            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findByAttachmentId(id);
//            if (contentOptional.isPresent()){
//                AttachmentContent attachmentContent = contentOptional.get();
//
//                response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getFileOriginalName()+"\"");
//
//                response.setContentType(attachment.getContentType());
//
//                FileCopyUtils.copy(attachmentContent.getMainContent(),response.getOutputStream());
//            }
//        }
//    }


//    @GetMapping("/getFileFromSystem/{id}")
//    public  void  getFileFromSystem(@PathVariable Integer id,HttpServletResponse response) throws IOException {
//        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
//        if (optionalAttachment.isPresent()){
//            Attachment attachment = optionalAttachment.get();
//
//            response.setHeader("Content-Disposition","attachment; filename=\""+attachment.getFileOriginalName()+"\"");
//
//            response.setContentType(attachment.getContentType());
//
//            FileInputStream fileInputStream=new FileInputStream(uploadDirectory+"/"+attachment.getName());
//
//            FileCopyUtils.copy(fileInputStream,response.getOutputStream());
//
//    }
//}



}