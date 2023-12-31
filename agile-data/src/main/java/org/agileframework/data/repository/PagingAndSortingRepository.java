package org.agileframework.data.repository;

import org.agileframework.data.domain.Page;
import org.agileframework.data.domain.Sort;

import java.util.List;

/**
 * @author xienng
 * @create 2023-12-30 21:41
 */
public interface PagingAndSortingRepository<T, ID> {

    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Integer current, Integer size, Sort sort);
}
