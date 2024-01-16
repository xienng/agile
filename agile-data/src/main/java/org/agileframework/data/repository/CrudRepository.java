package org.agileframework.data.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author xienng
 * @create 2023-12-30 21:38
 */
public interface CrudRepository<T, ID> {

    default T insert(T entity) {
        return null;
    }


    default T update(T entity) {
        return null;
    }

    default T updateWithLock(T entity) {
        return null;
    }

    default Collection<T> insertAll(Collection<T> entities) {
        return null;
    }


    default Optional<T> findById(ID id) {
        return Optional.empty();
    }


    default boolean existById(ID id) {
        return false;
    }


    default Collection<T> findAllById(Collection<ID> ids) {
        return Collections.EMPTY_LIST;
    }


    default Long count() {
        return null;
    }


    default void deleteById(ID id) {
    }


    default void delete(T entity) {
    }


    default void deleteAllById(Collection<ID> ids) {
    }


    default void deleteAll(Collection<T> entities) {
    }


}