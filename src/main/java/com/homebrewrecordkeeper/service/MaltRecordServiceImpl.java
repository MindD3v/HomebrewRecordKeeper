package com.homebrewrecordkeeper.service;

import com.homebrewrecordkeeper.repository.MaltRecordRepository;
import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MaltRecordServiceImpl implements MaltRecordService {
    @Autowired
    private MaltRecordRepository _maltRecordRepository;

    @Override
    @Transactional
    public MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity) {
        return _maltRecordRepository.addMaltRecord(maltRecordEntity);
    }

    @Override
    @Transactional
    public boolean deleteMaltRecord(int id) {
        MaltRecordEntity maltRecordEntity = _maltRecordRepository.getMaltRecordById(id);
        if(maltRecordEntity != null)
            return _maltRecordRepository.deleteMaltRecord(maltRecordEntity);
        else
            return false;
    }

    @Override
    @Transactional
    public MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity, int id){

        MaltRecordEntity maltRecordEntityToModify = _maltRecordRepository.getMaltRecordById(id);
        if(maltRecordEntityToModify != null)
        {
            maltRecordEntityToModify.setUnit(maltRecordEntity.getUnit());
            maltRecordEntityToModify.setName(maltRecordEntity.getName());
            maltRecordEntityToModify.setType(maltRecordEntity.getType());
            maltRecordEntityToModify.setAmount(maltRecordEntity.getAmount());

            return _maltRecordRepository.updateMaltRecord(maltRecordEntityToModify);
        }

        return null;
    }

    @Override
    @Transactional
    public MaltRecordEntity getMaltRecordById(int id) {
        return _maltRecordRepository.getMaltRecordById(id);
    }

    @Override
    @Transactional
    public List<MaltRecordEntity> getAll() {
        return _maltRecordRepository.getAll();
    }

    public void setMaltRecordRepository(MaltRecordRepository maltRecordRepository) {
        this._maltRecordRepository = maltRecordRepository;
    }

    public MaltRecordRepository getMaltRecordRepository() {
        return this._maltRecordRepository;
    }
}
