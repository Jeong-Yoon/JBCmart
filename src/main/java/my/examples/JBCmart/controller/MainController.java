package my.examples.JBCmart.controller;

//import my.examples.JBCmart.service.ProductService;
import my.examples.JBCmart.domain.Product;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.security.JBCSecurityUser;
import my.examples.JBCmart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String main(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "categoryId", required = false) Long categoryId,
                       @RequestParam(name = "searchKind", required = false) String searchKind,
                       @RequestParam(name = "searchStr", required = false) String searchStr,
                       Model model){

        List<Product> products = productService.getProducts(page, categoryId, searchKind, searchStr);
        model.addAttribute("products", products);
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            JBCSecurityUser jbcSecurityUser =
                    (JBCSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println("============================="+jbcSecurityUser.getName());
            model.addAttribute("user",jbcSecurityUser);
        }
        return "index";
    }


//
//    @GetMapping("/main")
//    public String main(Model model){
//        List<User> list = userService.getUsers();
//        model.addAttribute("list", list);
//        return "index2";
//    }

}