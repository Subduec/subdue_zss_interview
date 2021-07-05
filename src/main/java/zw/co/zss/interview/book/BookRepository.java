package zw.co.zss.interview.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.zss.interview.model.Book;
import zw.co.zss.interview.utils.Status;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

  //  List<Book> findByName(String Name);

    Book save(Book book);

    List<Book> findByStatus(Status status);

    List<Book> findByCategoryId(Long categoryId);


}

