package zw.co.zss.interview.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.zss.interview.model.Category;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Optional<Category> findById(Object id) {
		return categoryRepository.findById((Long) id);
	}

	@Override
	public List<Category> findAll(int page, int size) {
		return categoryRepository.findAll();
	}

	@Override
	public Optional<Category> create(Category category) {
		return Optional.ofNullable(categoryRepository.save(category));
	}

	@Override
	public Optional<Category> update(Category category) {
		return Optional.ofNullable(categoryRepository.save(category));
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}
}