package com.example.demo.controller;

import com.example.demo.entity.Test;
import com.example.demo.service.JobService;
import com.example.demo.service.TestService;
import com.example.demo.util.BatchContextHolder;
import lombok.Getter;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    TestService testService;

    @Autowired
    JobService jobService;

    @RequestMapping(value = "test", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Test> doTest(@RequestHeader(value = "tenantId") String tenantId){
        BatchContextHolder.setDataBaseName("db_" + tenantId);
        System.out.printf("id: ");

        // schema 미지정으로 오류
//        testService.getTestList_DefaultAndDefault();

        // schema는 지정되었으나 트랜잭션매니저와 마이바티스에서 사용하는 데이터소스가 달라 경계설정 안됨
//        testService.getTestList_DefaultAndBatch();

        // 정상동작
        testService.getTestListBatch_BatchAndBatch();
        return Collections.EMPTY_LIST;
    }

    @GetMapping("batch")
    public void batchTest(@RequestHeader(value = "tenantId") String tenantId) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        BatchContextHolder.setDataBaseName("db_" + tenantId);

        jobService.run();


        System.out.printf("return");
    }
}
