package com.dal.universityPortal.database;

import com.dal.universityPortal.model.University;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.database.query.UniversityQuery.FETCH_ALL_UNIVERSITIES;
import static com.dal.universityPortal.database.query.UniversityQuery.INSERT_UNIVERSITY;

@Component
public class UniversityDao implements SelectDao<University>,InsertDao<University>,UpdateDao<University> {

    @Override
    public List<University> fetchAll() throws SQLException {
        List<Map<String, Object>> uniList;
        List<University> universityList = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            uniList = dbSession.fetch(FETCH_ALL_UNIVERSITIES);
            dbSession.setAutoCommit(true);
            for (Map<String, Object> mapUni : uniList) {
                University university = new University();
                university.setUserId(Integer.parseInt(String.valueOf(mapUni.get("user_id"))));
                university.setUniversityName(String.valueOf(mapUni.get("university_name")));
                university.setUniversityDescription(String.valueOf(mapUni.get("university_description")));
                universityList.add(university);
            }
        }
        return universityList;
    }

    @Override
    public void insert(University university) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(INSERT_UNIVERSITY, Arrays.asList(university.getUserId(), university.getUniversityName(),
                    university.getUniversityDescription()));
        }
    }

    @Override
    public void update(University university) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            String query = "UPDATE university SET university_name = '"+university.getUniversityName()+"', university_description = '"+university.getUniversityDescription()+"' WHERE user_id = "+university.getUserId();
            dbSession.execute(query);
        }
    }
}
