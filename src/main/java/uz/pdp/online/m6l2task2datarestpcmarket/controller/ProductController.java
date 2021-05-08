package uz.pdp.online.m6l2task2datarestpcmarket.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;
import uz.pdp.online.m6l2task2datarestpcmarket.payload.ProductDto;
import uz.pdp.online.m6l2task2datarestpcmarket.payload.ProductSortDto;
import uz.pdp.online.m6l2task2datarestpcmarket.service.ProductService;
import uz.pdp.online.m6l2task2datarestpcmarket.service.Result;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;



    /**
     * Product ni filtr orqali berilgan parametrlar orqali topib olib ==>
     * kelish , filtrda berilgan parametrlar list <Integer> bilib keladi
     * list<Integer> productni ram,ssd,screen id larni berish qidirilvotgan parametrlar id larini listi
     * list<Integer> 4gb , 8 gb, 152ssd,,16dyum value id lari listi
     * @param page page lab qaytarish sahifalab
     * @return Product larni page i qaytadi.
     */

    @GetMapping("/search")
    public HttpEntity<Page<Product>> getList(@RequestBody ProductSortDto productSortDto, @RequestParam Integer page){

        Page<Product> products = productService.sortProduct(productSortDto.getFeature(), productSortDto.getValue(), page);

        return ResponseEntity.ok(products);

    }

    /**
     * Post product
     * @param productDto   dto for product class(entity)
     * @return Result
     */

    @PostMapping
    public ResponseEntity<?>add(@Valid @RequestBody ProductDto productDto){
        Result result = productService.add(productDto);
        return ResponseEntity.status(result.getSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }



    /**
     * all products list pageable
     * @param page Integer
     * @return Product
     */

    @GetMapping
    public ResponseEntity<?>AllProduct(@RequestParam Integer page){
        Page<Product> allProduct = productService.getAllProduct(page);
        return ResponseEntity.ok(allProduct);
    }




    /***
     * get one product
     * @param id integer
     * @return product
     */

    @GetMapping("/{id}")
    public HttpEntity<?>grtOne(@PathVariable Integer id){

        Product product = productService.getOne(id);
        return ResponseEntity.status(product!=null?HttpStatus.OK:HttpStatus.NOT_FOUND).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>edit(@PathVariable Integer id,@Valid @RequestBody ProductDto productDto){
        Result result = productService.edit(id, productDto);
        return ResponseEntity.status(result.getSuccess()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){

        Result result = productService.delete(id);
        return ResponseEntity.status(result.getSuccess()?200:404).body(result);

    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
