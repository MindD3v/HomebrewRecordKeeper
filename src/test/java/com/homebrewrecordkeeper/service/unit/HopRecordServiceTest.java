package com.homebrewrecordkeeper.service.unit;

import com.homebrewrecordkeeper.repository.HopRecordRepository;
import com.homebrewrecordkeeper.entity.HopRecordEntity;
import com.homebrewrecordkeeper.service.HopRecordService;
import com.homebrewrecordkeeper.service.HopRecordServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class HopRecordServiceTest {

    @Test
    public void addHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        HopRecordEntity createdHopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        createdHopRecordEntity.setId(1);

        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.addHopRecord(hopRecordEntity)).andReturn(createdHopRecordEntity);
        replay(hopRecordRepository);


        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        HopRecordEntity testedHopRecordEntity = hopRecordService.addHopRecord(hopRecordEntity);

        assertThat(testedHopRecordEntity.getId(),not(equalTo(0)));
        assertThat(testedHopRecordEntity.getAmount(),equalTo(2d));
        assertThat(testedHopRecordEntity.getType(),equalTo("Cascade"));
    }
    @Test
    public void updateHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);
        HopRecordEntity changedHopRecordEntity = new HopRecordEntity(5,"oz",60,"Perle",5);
        changedHopRecordEntity.setId(1);

        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getHopRecordById(hopRecordEntity.getId())).andReturn(hopRecordEntity);
        expect(hopRecordRepository.updateHopRecord(hopRecordEntity)).andReturn(changedHopRecordEntity);
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        HopRecordEntity testedHopRecordEntity = hopRecordService.updateHopRecord(changedHopRecordEntity, hopRecordEntity.getId());

        assertThat(testedHopRecordEntity.getId(),not(equalTo(0)));
        assertThat(testedHopRecordEntity.getAmount(),equalTo(5d));
        assertThat(testedHopRecordEntity.getType(),equalTo("Perle"));
    }
    @Test
    public void deleteHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);

        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getHopRecordById(hopRecordEntity.getId())).andReturn(hopRecordEntity);
        expect(hopRecordRepository.deleteHopRecord(hopRecordEntity)).andReturn(true);
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        boolean result = hopRecordService.deleteHopRecord(hopRecordEntity.getId());

        assertThat(result,equalTo(true));
    }
    @Test
    public void getAllHopRecordTest()
    {
        final HopRecordEntity test1 = new HopRecordEntity(2,"oz",60,"Cascade",5);
        final HopRecordEntity test2 = new HopRecordEntity(5,"oz",60,"Perle",5);
        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getAll()).andReturn(new ArrayList<>(Arrays.asList(test1, test2)));
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        List<HopRecordEntity> hopRecordEntityList = hopRecordService.getAll();

        assertThat(hopRecordEntityList.get(0),equalTo(test1));
        assertThat(hopRecordEntityList.get(1),equalTo(test2));
    }
    @Test
    public void getHopRecordByIdTest()
    {
        final HopRecordEntity test1 = new HopRecordEntity(2,"oz",60,"Cascade",5);
        test1.setId(1);
        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getHopRecordById(1)).andReturn(test1);
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        HopRecordEntity hopRecordEntity = hopRecordService.getHopRecordById(1);

        assertThat(hopRecordEntity.getId(),equalTo(1));
        assertThat(hopRecordEntity.getAlphaAcid(),equalTo(test1.getAlphaAcid()));
        assertThat(hopRecordEntity.getType(),equalTo(test1.getType()));
        assertThat(hopRecordEntity.getAmount(),equalTo(test1.getAmount()));
    }
    @Test
    public void getNotExistingHopRecordByIdTest()
    {
        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getHopRecordById(1)).andReturn(null);
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        HopRecordEntity hopRecordEntity = hopRecordService.getHopRecordById(1);

        assertThat(hopRecordEntity,equalTo(null));
    }
    @Test
    public void updateNotExistingHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);

        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getHopRecordById(1)).andReturn(null);
        expect(hopRecordRepository.updateHopRecord(hopRecordEntity)).andReturn(hopRecordEntity);
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        HopRecordEntity testedHopRecordEntity = hopRecordService.updateHopRecord(hopRecordEntity, hopRecordEntity.getId());

        assertThat(testedHopRecordEntity,equalTo(null));
    }
    @Test
    public void deleteNotExistingHopRecordTest()
    {
        HopRecordEntity hopRecordEntity = new HopRecordEntity(2,"oz",60,"Cascade",5);
        hopRecordEntity.setId(1);

        HopRecordRepository hopRecordRepository = createNiceMock(HopRecordRepository.class);
        expect(hopRecordRepository.getHopRecordById(hopRecordEntity.getId())).andReturn(null);
        expect(hopRecordRepository.deleteHopRecord(hopRecordEntity)).andReturn(false);
        replay(hopRecordRepository);

        HopRecordService hopRecordService = new HopRecordServiceImpl();
        hopRecordService.setHopRecordRepository(hopRecordRepository);
        boolean result = hopRecordService.deleteHopRecord(hopRecordEntity.getId());

        assertThat(result,equalTo(false));
    }
}
