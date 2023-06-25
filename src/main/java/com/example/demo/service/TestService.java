package com.example.demo.service;

import com.example.demo.dao.TestDao;
import com.example.demo.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestDao testDao;
    @Transactional
    public List<Test> getTestList_DefaultAndDefault(){
        testDao.getTestList();
        testDao.getTestList();
        testDao.getTestList();
        testDao.getTestList();
        testDao.insertTest();

        if(true){
            throw new RuntimeException();
        }
        testDao.getTestList();
        testDao.getTestList();
        testDao.getTestList();

        return testDao.getTestList();
    }

    @Transactional
    public List<Test> getTestList_DefaultAndBatch(){
        testDao.getTestListBatch();
        testDao.getTestListBatch();
        testDao.insertTestBatch();

        if(true){
            throw new RuntimeException();
        }
        testDao.getTestList();
        testDao.getTestList();
        testDao.getTestList();

        return testDao.getTestList();
    }
    @Transactional("batchTransactionManager")
    public List<Test> getTestListBatch_BatchAndBatch(){
        testDao.getTestListBatch();
        testDao.getTestListBatch();
        testDao.getTestListBatch();
        testDao.getTestListBatch();
        testDao.insertTestBatch();

        if(true){
            throw new RuntimeException();
        }
        testDao.getTestListBatch();
        testDao.getTestListBatch();
        testDao.getTestListBatch();

        return testDao.getTestListBatch();
    }

}
