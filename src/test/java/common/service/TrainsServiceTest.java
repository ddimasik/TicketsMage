package common.service;

import common.model.TrainEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static common.service.MocksConfiguration.trainsRepository;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TrainsService.class, MocksConfiguration.class})

public class TrainsServiceTest {

    @Autowired
    private TrainsService trainsService;

    @Test
    public void testAddition(){
        Assert.assertEquals(18, trainsService.addition(1,17));
    }

    @Test
    public void testEmptyGetAllTrains(){
        when(trainsRepository.getAllTrains()).thenReturn(Collections.emptyList());
        List<TrainEntity> all = trainsService.findAll();
        Assert.assertTrue(all.isEmpty());
    }

    @Test
    public void testFindById(){
        TrainEntity trainEntity = new TrainEntity();
        trainEntity.setCapacity(33);
        when(trainsRepository.findById(1)).thenReturn(trainEntity);
        Assert.assertEquals(trainEntity.getCapacity(), trainsService.findById(1).getCapacity());
    }

}
