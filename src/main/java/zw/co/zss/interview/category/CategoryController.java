package zw.co.zss.interview.category;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zw.co.zss.interview.commons.ApiResponse;
import zw.co.zss.interview.exception.BusinessException;
import zw.co.zss.interview.exception.RecordNotFound;
import zw.co.zss.interview.model.Category;
import zw.co.zss.interview.utils.Status;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public List<Category> getAllCategorys()

    {
        return categoryService.findAll(0,1000);
    }

    @PostMapping("/create")
    public ApiResponse<Category> create(@RequestBody Category category) {
      logger.info("category " +category);
        category = categoryService.create(category).orElseThrow(() -> new BusinessException("Error Creating Category"));
        return new ApiResponse<>(HttpStatus.OK.value(), category);
    }

    @PostMapping("/update")
    public ApiResponse<Category> update(@RequestBody Category category) {
       // log.info("Updating Category:{}", category);
        category = categoryService.update(category).orElseThrow(() -> new BusinessException("Error Updating Category"));
        return new ApiResponse<>(HttpStatus.OK.value(), category);
    }

    @GetMapping("/findbyid/{id}")
    public ApiResponse<Category> findById(@PathVariable("id") Long id){
        Category category = categoryService.findById(id).orElseThrow(()->new RecordNotFound("Record Not Found"));
        logger.info("Test : "+category);
        return new ApiResponse<>(HttpStatus.OK.value(),category);
    }

    @GetMapping("/delete/{id}")
    public ApiResponse<Category> delete(@PathVariable("id") Long id){
        Category category = categoryService.findById(id).orElseThrow(()->new RecordNotFound("Error Deleting. Record Not Found"));
        category.setStatus(Status.DELETED);
        categoryService.update(category);
        return new ApiResponse<>(HttpStatus.OK.value(),category);
    }


}
