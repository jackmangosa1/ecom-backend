//package com.auca.Shopy.controller.customer;
//
//import com.auca.Shopy.dto.AddProductInCartDTO;
//import com.auca.Shopy.dto.OrderDTO;
//import com.auca.Shopy.service.customer.cart.CartService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/customer")
//@RequiredArgsConstructor
//public class CartController {
//
//    private final CartService cartService;
//
//    @PostMapping("/cart")
//    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDTO addProductInCartDTO){
//        return  cartService.addProductToCart(addProductInCartDTO);
//    }
//
//    @GetMapping("/cart/{$userId}")
//    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId){
//        OrderDTO orderDTO = cartService.getCartByUserId(userId);
//        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
//    }
//}
