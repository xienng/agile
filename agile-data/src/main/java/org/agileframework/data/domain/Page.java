package org.agileframework.data.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 简单分页模型
 *
 * @author xienng
 * @create 2023-12-29 20:01
 */
@Data
@NoArgsConstructor
public class Page<T> {

    /**
     * 查询数据列表
     */
    protected List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    protected Long total;
    /**
     * 每页显示条数，默认 10
     */
    protected Long size;


    protected Long pages;

    /**
     * 当前页
     */
    protected Long current;

    /**
     * 可以用于排序的字段
     */
    protected List<String> sortFields = new ArrayList<>();


    /**
     * 判断当前结果是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.total == 0L;
    }

    /**
     * 将一个Page对象快速转换成目标Page对象
     *
     * @param other
     * @param function
     * @param <R>
     */
    public <R> Page(Page<R> other, Function<R, T> function) {
        this.records = other.records.stream().map(function)
                .collect(Collectors.toList());
        this.pages = other.pages;
        this.total = other.total;
        this.size = other.size;
        this.pages = other.pages;
        this.current = other.current;
    }
}

