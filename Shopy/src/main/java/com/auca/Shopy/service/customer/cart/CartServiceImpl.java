package com.auca.Shopy.service.customer.cart;

import com.auca.Shopy.dto.AddProductInCartDTO;
import com.auca.Shopy.dto.CartItemDTO;
import com.auca.Shopy.dto.OrderDTO;
import com.auca.Shopy.enums.OrderStatus;
import com.auca.Shopy.model.CartItem;
import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.Product;
import com.auca.Shopy.model.User;
import com.auca.Shopy.repository.CartItemRepository;
import com.auca.Shopy.repository.OrderRepository;
import com.auca.Shopy.repository.ProductRepository;
import com.auca.Shopy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    public ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO){
//        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
//        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndOrderIdAndUserId(addProductInCartDTO.getProductId(), activeOrder.getId(), addProductInCartDTO.getUserId());
//
//        if(optionalCartItem.isPresent()){
//            return  ResponseEntity.status(HttpStatus.CONFLICT).body(null);
//        } else{
//            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDTO.getProductId());
//            Optional<User> optionalUser = userRepository.findById(addProductInCartDTO.getUserId());
//
//            if(optionalProduct.isPresent() && optionalUser.isPresent()){
//                CartItem cart = new CartItem();
//                cart.setProduct(optionalProduct.get());
//                int sizeIndex = addProductInCartDTO.getSizeIndex(); //
//                cart.setPrice(optionalProduct.get().getPrice()[sizeIndex]);
//                cart.setQuantity(1L);
//                cart.setUser(optionalUser.get());
//                cart.setOrder(activeOrder);
//
//                CartItem updatedCart = cartItemRepository.save(cart);
//                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
//                activeOrder.getCartItems().add(cart);
//                orderRepository.save(activeOrder);
//                return  ResponseEntity.status(HttpStatus.CREATED).body(cart);
//            } else{
//                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user or product not found");
//            }
//        }
//    }
//
//    public OrderDTO getCartByUserId(Long userId){
//        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
//        List<CartItemDTO> cartItemDTOList = activeOrder.getCartItems().stream().map(CartItem::getCartDTO).toList();
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setAmount(activeOrder.getAmount());
//        orderDTO.setId(activeOrder.getId());
//        orderDTO.setAmount(activeOrder.getAmount());
//        orderDTO.setCartItems(cartItemDTOList);
//        return orderDTO;
//    }
}
