package com.example.demo.dao;

import com.example.demo.entity.Test;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDao {
    @Autowired
    SqlSession sqlSession;

    @Autowired
    @Qualifier("batchSqlSessionTemplate")
    SqlSession batchSqlSession;

    public List<Test> getTestList() {
        return sqlSession.selectList("testSelect");
    }

    public void insertTest() {
        sqlSession.insert("testInsert");
    }

    public List<Test> getTestListBatch() {
        return batchSqlSession.selectList("testSelect");
    }

    public void insertTestBatch() {
        batchSqlSession.insert("testInsert");
    }
}
