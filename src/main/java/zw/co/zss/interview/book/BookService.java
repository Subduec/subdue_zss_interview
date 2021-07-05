package zw.co.zss.interview.book;

import zw.co.zss.interview.commons.AppService;
import zw.co.zss.interview.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService extends AppService<Book> {

    List<Book> findByCategoryId(Long categoryId);
}
