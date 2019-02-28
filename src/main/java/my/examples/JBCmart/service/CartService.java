package my.examples.JBCmart.service;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Cart;
import my.examples.JBCmart.domain.CartId;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.repository.CartRepository;
import my.examples.JBCmart.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Cart> getCart(String userId) {
        return cartRepository.getCarts(userId);
    }

    @Transactional
    public Cart addCart(Cart cart,String userId) {
        CartId cartId = new CartId();
        User user = userRepository.getUserById(userId);
        System.out.println("*******************");
        if (cartRepository.getSeq(userId) != null){
            cartId.setSeq(cartRepository.getSeq(userId)+1);
        }else {
            cartId.setSeq(1);
        }
        cartId.setUser(user);
        cart.setCartId(cartId);
        return cartRepository.save(cart);
    }
}
