package my.examples.JBCmart.controller;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.ImageFile;
import my.examples.JBCmart.domain.Product;
import my.examples.JBCmart.security.JBCSecurityUser;
import my.examples.JBCmart.service.ImageFileService;
import my.examples.JBCmart.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ImageFileService imageFileService;

    @GetMapping("/{id}")
    public String view(@PathVariable(name = "id") String id,
                       Model model){
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product/view";
    }

    @GetMapping("/images/{id}")
    @ResponseBody // 컨트롤러안에서 직접 response를 이용하여 결과를 출력할 때 사용
    public void downloadImage(
            @PathVariable(name = "id") Long id,
            HttpServletResponse response
    ) {
        ImageFile imageFile = imageFileService.getImageFile(id);
        response.setContentType(imageFile.getMimeType());

        try(FileInputStream fis = new FileInputStream(imageFile.getSaveFileName());
            OutputStream out = response.getOutputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;

            while((readCount = fis.read(buffer)) != -1){
                out.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // 로그인을 한 사용자 정보는 Security필터에서 SecurityContextHolder의 ThreadLocal에 저장된다.
    // 그래서 같은 쓰레드상이라면 로그인한 정보를 읽어들일 수 있다.
    // authentication.principal
//    JBCSecurityUser jbcSecurityUser =
//            (JBCSecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
}
