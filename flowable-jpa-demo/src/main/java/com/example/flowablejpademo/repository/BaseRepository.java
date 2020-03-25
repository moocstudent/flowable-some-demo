package com.example.flowablejpademo.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T,Long> extends PagingAndSortingRepository<T,Long> {

    /**
     * 根据创建时间选择最新的数据
     * @return
     */
    List<T> findAllByOrderByCreateTimeDesc();
}