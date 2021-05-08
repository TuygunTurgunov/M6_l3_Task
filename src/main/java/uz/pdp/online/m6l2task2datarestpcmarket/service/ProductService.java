package uz.pdp.online.m6l2task2datarestpcmarket.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import org.springframework.web.bind.annotation.PutMapping;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.*;
import uz.pdp.online.m6l2task2datarestpcmarket.payload.ProductDto;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    ValueRepository valueRepository;


    /**
     * Product ni filtr orqali berilgan parametrlar orqali topib olib ==>
     * kelish , filtrda berilgan parametrlar list <Integer> bilib keladi
     *
     * @param feature list<Integer> productni ram,ssd,screen id larni berish qidirilvotgan parametrlar id larini listi
     * @param value   list<Integer> 4gb , 8 gb, 152ssd,,16dyum value id lari listi
     * @param page    page lab qaytarish sahifalab
     * @return Product larni page i qaytadi.
     */


    public Page<Product> sortProduct(List<Integer> feature, List<Integer> value, Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return productRepository.getProductByValue(feature, value, pageable);

    }

    /**
     * Post product
     *
     * @param productDto dto for product class(entity)
     * @return Result
     */
    public Result add(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory());
        if (!optionalCategory.isPresent())
            return new Result("category not found by id", false);

        boolean exists = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCurrencyId());
        if (exists)
            return new Result("such kind of product already exists in this category", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("attachment not found by id ", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(productDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found by id", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement not found by id", false);

        List<Value> valueList = valueRepository.findAllById(productDto.getValueId());
        if (valueList.isEmpty())
            return new Result("Product must have any parameter", false);


        Product product = new Product();
        product.setName(productDto.getName());
        product.setProductCode(productDto.getProductCode());
        product.setCategory(optionalCategory.get());
        product.setCurrency(optionalCurrency.get());
        product.setPrice(productDto.getPrice());
        product.setGuarantee(productDto.getGuarantee());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachmentPhoto(optionalAttachment.get());
        product.setValue(valueList);

        productRepository.save(product);
        return new Result("product saved", true);

    }


    /**
     * all products list pageable
     *
     * @param page Integer
     * @return Product
     */
    public Page<Product> getAllProduct(Integer page) {
        Pageable pageable = PageRequest.of(page, 3);
        return productRepository.findAll(pageable);
    }

    /***
     * get one product
     * @param id integer
     * @return product
     */

    public Product getOne(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }


    public Result edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Product already exists", false);


        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory());
        if (!optionalCategory.isPresent())
            return new Result("Category not found", false);


        boolean exists = productRepository.existsByNameAndCategoryIdNotAndIdNot(productDto.getName(), productDto.getCategory(), id);
        if (exists)
            return new Result("product such kind of name already exists", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("attachment not found by id ", false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(productDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found by id", false);

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Measurement not found by id", false);

        List<Value> valueList = valueRepository.findAllById(productDto.getValueId());
        if (valueList.isEmpty())
            return new Result("Product must have any parameter", false);


        Product product = optionalProduct.get();

        product.setName(productDto.getName());
        product.setProductCode(productDto.getProductCode());
        product.setCategory(optionalCategory.get());
        product.setCurrency(optionalCurrency.get());
        product.setPrice(productDto.getPrice());
        product.setGuarantee(productDto.getGuarantee());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachmentPhoto(optionalAttachment.get());
        product.setValue(valueList);

        productRepository.save(product);
        return new Result("edited", true);

    }


    public  Result delete(Integer id){
        try {

        productRepository.deleteById(id);
        return new Result("deleted",true);
        }catch (Exception e){
            return new Result("no delete",false);
        }



    }

}