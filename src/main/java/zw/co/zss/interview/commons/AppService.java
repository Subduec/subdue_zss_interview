package zw.co.zss.interview.commons;

import java.util.List;
import java.util.Optional;

public interface AppService<T> {

    Optional<T> findById(Object id);

    List<T> findAll(int page, int size);

    Optional<T> create(T t);

    Optional<T> update(T t);

    void delete(T t);

}
