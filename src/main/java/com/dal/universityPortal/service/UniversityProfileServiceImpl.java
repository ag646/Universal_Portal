package com.dal.universityPortal.service;

import com.dal.universityPortal.database.UniversityDao;
import com.dal.universityPortal.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UniversityProfileServiceImpl implements UniversityProfileService {

    @Autowired
    private UniversityDao universityDao;

    @Override
    public Boolean saveProfile(University university) throws SQLException {
        try {
            universityDao.insert(university);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    @Override
    public University readProfile(int id) throws SQLException {
        University university = new University();
        List<University> universityList = universityDao.fetchAll();
        for (University uniList : universityList) {
            if (uniList.getUserId() == id) {
                university.setUserId(uniList.getUserId());
                university.setUniversityName(uniList.getUniversityName());
                university.setUniversityDescription(uniList.getUniversityDescription());
            }
        }
        return university;
    }

    @Override
    public Boolean updateProfile(University university) throws SQLException {
        universityDao.update(university);
        return true;
    }
}
