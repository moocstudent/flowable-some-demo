package com.example.flowablejpademo.repository;

import com.example.flowablejpademo.bean.MyProcess;
import org.springframework.stereotype.Repository;

@Repository
public interface MyProcessRepository extends BaseRepository<MyProcess,Long> {
    /**
     * 根据processKey查部署process实例(非flowable官方表)
     * @param processKey
     * @return
     */
    MyProcess findByProcessKey(String processKey);

}
