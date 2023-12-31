package org.agileframework.data.repository;

import java.util.Collection;
import java.util.Optional;

/**
 * @author xienng
 * @create 2023-12-30 21:38
 */
public interface CrudRepository<T, ID> {

    T insert(T entity);

    T update(T entity);

    Collection<T> insertAll(Collection<T> entities);

    Optional<T> findById(ID id);

    boolean existById(ID id);

    Collection<T> findAllById(Iterable<ID> ids);

    Long count();

    void deleteById(ID id);

    void delete(T entity);

    void deleteAllById(Collection<ID> ids);

    void deleteAll(Collection<T> entities);

    void deleteAll();
}