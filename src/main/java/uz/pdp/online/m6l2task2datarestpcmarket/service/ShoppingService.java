package uz.pdp.online.m6l2task2datarestpcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Currency;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Product;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.Shopping;
import uz.pdp.online.m6l2task2datarestpcmarket.entity.User;
import uz.pdp.online.m6l2task2datarestpcmarket.payload.ShoppingDto;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.CurrencyRepository;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.ProductRepository;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.ShoppingRepository;
import uz.pdp.online.m6l2task2datarestpcmarket.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ShoppingRepository shoppingRepository;

    @Autowired
    ProductRepository productRepository;


    public  Result add(ShoppingDto shoppingDto){


        Optional<User> optionalUser = userRepository.findById(shoppingDto.getUserId());
        if (!optionalUser.isPresent())
            return new Result("user not found",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(shoppingDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found",false);

        List<Product> productList = productRepository.findAllById(shoppingDto.getProductIdList());
        if (productList.isEmpty())
            return new Result("product list not be empty",false);

        Shopping shopping=new Shopping();
        shopping.setUser(optionalUser.get());
        shopping.setCurrency(optionalCurrency.get());
        shopping.setProduct(productList);
        shopping.setPrice(shoppingDto.getPrice());
        shopping.setAmount(shoppingDto.getAmount());
        shopping.setTotalPrice(shoppingDto.getTotalPrice());
        shoppingRepository.save(shopping);
        return new Result("Saved",true);

    }


    public Result edit(Integer id, ShoppingDto shoppingDto) {

        Optional<Shopping> optionalShopping = shoppingRepository.findById(id);
        if (!optionalShopping.isPresent())
            return new Result("shopping not found",false);

        Optional<User> optionalUser = userRepository.findById(shoppingDto.getUserId());
        if (!optionalUser.isPresent())
            return new Result("user not found",false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(shoppingDto.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("currency not found",false);

        List<Product> productList = productRepository.findAllById(shoppingDto.getProductIdList());
        if (productList.isEmpty())
            return new Result("product list not be empty",false);

        Shopping shopping = optionalShopping.get();
        shopping.setPrice(shoppingDto.getPrice());
        shopping.setAmount(shoppingDto.getAmount());
        shopping.setProduct(productList);
        shopping.setCurrency(optionalCurrency.get());
        shopping.setUser(optionalUser.get());
        shopping.setTotalPrice(shoppingDto.getTotalPrice());
        shoppingRepository.save(shopping);
        return new Result("shopping editef",true);




    }

    public Page<Shopping> getAll(Integer page) {
        Pageable pageable= PageRequest.of(page,2);
        return shoppingRepository.findAll(pageable);
    }

    public Shopping getOne(Integer id) {

        Optional<Shopping> optionalShopping = shoppingRepository.findById(id);
        return optionalShopping.orElse(null);


    }

    public Result delete(Integer id) {
        try {
            shoppingRepository.deleteById(id);
            return new Result("deleted",true);
        }catch (Exception e){
            return new Result("no deleted",false);
        }


    }
}
