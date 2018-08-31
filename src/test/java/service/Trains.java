package service;

import common.model.Train;
import common.service.TrainsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@WebAppConfiguration
//public class Trains {
//
//
//
//
//    @Autowired
//    private TrainsService trainsService;
//
//    @Test
//    public void CreateNewTrain(){
//        Train trainExpected = new Train();
//        trainExpected.setName("Red piggy");
//        trainExpected.setCapacity(800);
//
//
//
//
//        trainsService.addTrain();
//
//        Assert.assertEquals(trainExpected, trainsService.findAll().get(0));
//    }
//}
