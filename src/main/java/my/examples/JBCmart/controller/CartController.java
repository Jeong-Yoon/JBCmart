package my.examples.JBCmart.controller;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Cart;
import my.examples.JBCmart.domain.CartId;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.repository.CartRepository;
import my.examples.JBCmart.repository.UserRepository;
import my.examples.JBCmart.security.JBCSecurityUser;
import my.examples.JBCmart.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartRepository cartRepository;

    @GetMapping("")
    public String cart(Model model){
        JBCSecurityUser jbcSecurityUser =
                (JBCSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Cart> carts = cartService.getCart(jbcSecurityUser.getUserId());
        model.addAttribute("carts",carts);
        return "cart/cart";
    }

    @PostMapping("/add")
    public String cart(@RequestParam(name = "size") String size,
                       @RequestParam(name = "color") String color,
                       @RequestParam(name = "quantity") int quantity,
                       @RequestParam(name = "productId") String productId,
                       Model model){
        JBCSecurityUser jbcSecurityUser =
                (JBCSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("......................"+size);
        System.out.println("/////////////////"+cartRepository.getSeq(jbcSecurityUser.getUserId()));
        Cart cart = new Cart();
        cart.setQuantity(quantity);
        cart.setProductColor(color);
        cart.setProductSize(size);
        cart.setProductId(productId);
        cartService.addCart(cart, jbcSecurityUser.getUserId());
        return "cart/cart";

    }
}
