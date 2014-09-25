package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.repository.HopRecordRepository;
import com.homebrewrecordkeeper.entity.HopRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopRecordServiceImpl implements HopRecordService {
    @Autowired
    private HopRecordRepository _hopRecordRepository;
    @Override
    public HopRecordEntity addHopRecord(HopRecordEntity hopRecordEntity) {
        return _hopRecordRepository.addHopRecord(hopRecordEntity);
    }

    @Override
    public boolean deleteHopRecord(int id) {
        HopRecordEntity hopRecordEntity = _hopRecordRepository.getHopRecordById(id);
        if(hopRecordEntity!=null)
            return _hopRecordRepository.deleteHopRecord(hopRecordEntity);
        else
            return false;
    }

    @Override
    public HopRecordEntity updateHopRecord(HopRecordEntity hopRecordEntity, int id) {
        HopRecordEntity hopRecordById = _hopRecordRepository.getHopRecordById(id);
        if(hopRecordById != null)
        {
            hopRecordById.setType(hopRecordEntity.getType());
            hopRecordById.setUnit(hopRecordEntity.getUnit());
            hopRecordById.setAmount(hopRecordEntity.getAmount());
            hopRecordById.setAlphaAcid(hopRecordEntity.getAlphaAcid());
            hopRecordById.setTimeInMinutes(hopRecordEntity.getTimeInMinutes());

            return _hopRecordRepository.updateHopRecord(hopRecordById);
        }
        return null;
    }

    @Override
    public HopRecordEntity getHopRecordById(int id) {
        return _hopRecordRepository.getHopRecordById(id);
    }

    @Override
    public List<HopRecordEntity> getAll() {
        return _hopRecordRepository.getAll();
    }

    @Override
    public void setHopRecordRepository(HopRecordRepository hrd) {
        _hopRecordRepository = hrd;
    }

    @Override
    public HopRecordRepository getHopRecordRepository() {
        return _hopRecordRepository;
    }
}
