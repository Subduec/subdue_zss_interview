package zw.co.zss.interview.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.zss.interview.model.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Optional<Book> findById(Object id) {
		return bookRepository.findById((Long) id);
	}

	@Override
	public List<Book> findAll(int page, int size) {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> create(Book book) {
		return Optional.ofNullable(bookRepository.save(book));
	}

	@Override
	public Optional<Book> update(Book book) {
		return Optional.ofNullable(bookRepository.save(book));
	}

	@Override
	public void delete(Book book) {
		bookRepository.delete(book);
	}

	@Override
	public List<Book> findByCategoryId(Long categoryId) {
		return bookRepository.findByCategoryId(categoryId);
	}
}