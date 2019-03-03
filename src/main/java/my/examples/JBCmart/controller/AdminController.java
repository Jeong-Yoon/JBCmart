package my.examples.JBCmart.controller;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.*;
import my.examples.JBCmart.security.JBCSecurityUser;
import my.examples.JBCmart.service.CategoryService;
import my.examples.JBCmart.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/register")
    public String register(Model model) {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
        return "admin/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(name = "id") String id,
                           @RequestParam(name = "name") String name,
                           @RequestParam(name = "price") int price,
                           @RequestParam(name = "categoryId") Long categoryId,
                           @RequestParam(name = "image") MultipartFile[] images,
                           Model model) {

        Assert.hasText(id, "id를 입력하세요.");
        Assert.hasText(name, "name을 입력하세요.");

        // 로그인을 한 사용자 정보는 Security필터에서 SecurityContextHolder의 ThreadLocal에 저장된다.
        // 그래서 같은 쓰레드상이라면 로그인한 정보를 읽어들일 수 있다.
        // authentication.principal
        JBCSecurityUser jbcSecurityUser =
                (JBCSecurityUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Product product = new Product();
        product.setProductId(id);
        product.setProductName(name);
        product.setPrice(price);

        if(images != null && images.length > 0) {
            int ordering = 1;
            for (MultipartFile image : images) {
                if (!image.isEmpty()){
                    ImageFile imageFile = new ImageFile();
                    imageFile.setLength(image.getSize());
                    imageFile.setMimeType(image.getContentType());
                    imageFile.setName(image.getOriginalFilename());
                    imageFile.setOrdering(ordering++);
                    // 파일 저장
                    // /tmp/2019/2/12/123421-12341234-12341234-123423142
                    String saveFileName = saveFile(image);

                    imageFile.setSaveFileName(saveFileName); // save되는 파일명
                    product.addImageFile(imageFile);
                }
            }
        }

        productService.addProduct(product,categoryId);
        model.addAttribute("product",product);
        return "admin/register-detail";
    }

    @GetMapping("/register-detail")
    public String registerDetail(){
        return "admin/register-detail";
    }

    @PostMapping("/register-detail")
    public String registerDetail(@RequestParam(name = "size") String size,
                                 @RequestParam(name = "color") String color,
                                 @RequestParam(name = "quantity") int quantity,
                                 @RequestParam(name = "productId") String productId,
                                 Model model){

        ProductDetailId productDetailId = new ProductDetailId();
        productDetailId.setProductColor(color);
        productDetailId.setProductSize(size);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setQuantity(quantity);

        System.out.println("111111111111"+productDetail);
        System.out.println("222222222"+productId);
        System.out.println("333333333333"+productDetailId);
        productService.addProductDetail(productDetail,productDetailId,productId);
        return "redirect:/main";
    }

    private String saveFile(MultipartFile image){
        String dir = "/tmp/";
        Calendar calendar = Calendar.getInstance();
        dir = dir + calendar.get(Calendar.YEAR);
        dir = dir + "/";
        dir = dir + (calendar.get(Calendar.MONTH) + 1);
        dir = dir + "/";
        dir = dir + calendar.get(Calendar.DAY_OF_MONTH);
        dir = dir + "/";
        File dirFile = new File(dir);
        dirFile.mkdirs(); // 디렉토리가 없을 경우 만든다. 퍼미션이 없으면 생성안될 수 있다.
        dir = dir + UUID.randomUUID().toString();

        try(FileOutputStream fos = new FileOutputStream(dir);
            InputStream in = image.getInputStream()
        ){
            byte[] buffer = new byte[1024];
            int readCount = 0;
            while((readCount = in.read(buffer)) != -1){
                fos.write(buffer, 0, readCount);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return dir;
    }

}
