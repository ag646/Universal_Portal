package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ProgramDao;
import com.dal.universityPortal.database.UniversityDao;
import com.dal.universityPortal.model.Program;
import com.dal.universityPortal.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SearchUniversityServiceImpl implements SearchUniversityService {

    @Autowired
    private UniversityDao universityDao;

    @Autowired
    private ProgramDao programDao;

    @Override
    public University getUniversityDetails(University university) throws SQLException {
        University universityDetail = new University();
        List<University> universityDetails = universityDao.fetchAll();
        for (University detail : universityDetails) {
            if (detail.getUniversityName().equals(university.getUniversityName())) {
                universityDetail.setUserId(detail.getUserId());
                universityDetail.setUniversityName(detail.getUniversityName());
                universityDetail.setUniversityDescription(detail.getUniversityDescription());
            } else {
                universityDetail.setUniversityName("");
            }
        }
        return universityDetail;
    }

    @Override
    public List<Program> getProgramDetails(int id) throws SQLException {
        return programDao.fetchAllByUniversityId(id);
    }
}
