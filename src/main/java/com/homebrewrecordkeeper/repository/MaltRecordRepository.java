package com.homebrewrecordkeeper.repository;

import com.homebrewrecordkeeper.entity.MaltRecordEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public interface MaltRecordRepository {
    MaltRecordEntity addMaltRecord(MaltRecordEntity maltRecordEntity);
    boolean deleteMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity updateMaltRecord(MaltRecordEntity maltRecordEntity);
    MaltRecordEntity getMaltRecordById(int id);
    List<MaltRecordEntity> getAll();
    void setSessionFactory(SessionFactory sessionFactory);
    SessionFactory getSessionFactory();
}
