package zw.co.zss.interview.category;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.interview.model.Category;
import zw.co.zss.interview.utils.Status;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

   // List<Category> findByName(String Name);

    Category save(Category category);

    List<Category> findByStatus(Status status);


}

