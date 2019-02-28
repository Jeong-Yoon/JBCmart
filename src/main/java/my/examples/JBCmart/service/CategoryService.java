package my.examples.JBCmart.service;

import lombok.RequiredArgsConstructor;
import my.examples.JBCmart.domain.Category;
import my.examples.JBCmart.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> getAll(){
        System.out.println(categoryRepository.getAll());
        return categoryRepository.getAll();
    }
}
