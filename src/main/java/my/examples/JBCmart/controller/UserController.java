package my.examples.JBCmart.controller;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.User;
import my.examples.JBCmart.dto.Join;
import my.examples.JBCmart.service.UserService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "fail",
                    required = false,
                    defaultValue = "false") String errorFlag, HttpServletRequest request){
        String referrer = request.getHeader("Referrer");
        request.getSession().setAttribute("prevPage", referrer);
        return "users/login"; // view name 을 리턴한다.
    }

    @GetMapping("/logout")
    public String logout(){
        return "/main";
    }

    @GetMapping("/join")
    public String joinform(){
        return "users/joinform";
    }

    // Form데이터를 DTO로 파라미터를 받아들일 경우엔 @ModelAttribute JoinForm joinForm
    // DTO에 Validation관련 어노테이션을 사용했을 경우에는 @Valid를 사용한다.
    @PostMapping("/join")
    public String join(@Valid Join join, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException(bindingResult.toString());
        }
        if (!join.getPassword1().equals(join.getPassword2())){
            throw new IllegalArgumentException("암호가 일치하지 않습니다.");
        }

        User user = new User();
        user.setUserId(join.getId());
        user.setName(join.getName());
        user.setEmail(join.getEmail());
        user.setPhone(join.getPhone());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPasswd(passwordEncoder.encode(join.getPassword1()));

        User result = userService.join(user);
        return "redirect:/index";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "users/welcome";
    }

//    UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/list")
//    public String main(Model model){
//        List<User> users = userService.getUsers();
//        model.addAttribute("users", users);
//        return "index";
//    }
}
