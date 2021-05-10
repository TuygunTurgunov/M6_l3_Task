package uz.pdp.online.m6l2task2datarestpcmarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Shopping;
import uz.pdp.online.m6l2task2datarestpcmarket.payload.ShoppingDto;
import uz.pdp.online.m6l2task2datarestpcmarket.service.Result;
import uz.pdp.online.m6l2task2datarestpcmarket.service.ShoppingService;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {
    @Autowired
    ShoppingService shoppingService;


    @PreAuthorize(value = "hasAuthority('ADD_SHOPPING')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody ShoppingDto shoppingDto){

        Result result = shoppingService.add(shoppingDto);
        return ResponseEntity.status(result.getSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_SHOPPING')")
    @PutMapping("/{id}")
    public HttpEntity<?>edit(@PathVariable Integer id ,@Valid @RequestBody ShoppingDto shoppingDto){

        Result result= shoppingService.edit(id,shoppingDto);
        return ResponseEntity.status(result.getSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_SHOPPING')")
    @GetMapping
    public HttpEntity<?>getAll(@RequestParam Integer page){
        Page<Shopping> shoppingPage = shoppingService.getAll(page);
        return ResponseEntity.ok(shoppingPage);
    }

    @PreAuthorize(value = "hasAuthority('READ_ONE_SHOPPING')")
    @GetMapping("/{id}")
    public HttpEntity<?>getOne(@PathVariable Integer id){
        Shopping shopping=shoppingService.getOne(id);
        return ResponseEntity.status(shopping!=null?200:409).body(shopping);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_SHOPPING')")
    @DeleteMapping("/{id}")
    public  HttpEntity<?>delete(@PathVariable Integer id){

        Result result= shoppingService.delete(id);
        return ResponseEntity.status(result.getSuccess()?200:409).body(result);
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
