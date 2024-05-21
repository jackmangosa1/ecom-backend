package com.auca.Shopy.model;

import com.auca.Shopy.dto.CartItemDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private  Long quantity;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public CartItemDTO getCartDTO(){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(id);
        cartItemDTO.setPrice(price);
        cartItemDTO.setProductId(product.getId());
        cartItemDTO.setQuantity(quantity);
        cartItemDTO.setUserId(user.getId());
        cartItemDTO.setProductName(product.getName());
        cartItemDTO.setReturnedImage(product.getImage());

        return  cartItemDTO;

    }
}
