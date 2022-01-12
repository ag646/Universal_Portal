package com.dal.universityPortal.database;

import com.dal.universityPortal.model.Application;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.constant.CommonConstant.INITIAL_APPLICATION_STATUS;
import static com.dal.universityPortal.constant.CommonConstant.INITIAL_OUTCOME_TYPE;
import static com.dal.universityPortal.database.query.ApplicationQuery.*;
import static com.dal.universityPortal.database.query.DashboardQuery.APPLICATIONS_FROM_STUDENT_ID;
import static com.dal.universityPortal.database.query.PaymentQuery.FOREIGN_KEY_CHECKS;

@Service
public class ApplicationDao implements InsertDao<Application> {

    public Application fetchAllByParam(int id) throws SQLException {
        List<Map<String, Object>> applicationlist;
        Application application = new Application();
        try (DBSession dbSession = new DBSession()) {
            applicationlist = dbSession.fetch(String.format(FETCH_APPLICATION_BY_ID_QUERY, id));
            for (Map<String, Object> applist : applicationlist) {
                application.setApplication_id(Integer.parseInt(String.valueOf(applist.get("id"))));
            }
        }
        return application;
    }

    public List<Application> fetchApplication(int student_id) throws SQLException {
        List<Map<String, Object>> applicationList;
        List<Application> applications = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            applicationList = dbSession.fetch(APPLICATIONS_FROM_STUDENT_ID, Arrays.asList(student_id));
            for (Map<String, Object> mapApplication : applicationList) {
                Application application = new Application();
                application.setApplication_id(Integer.parseInt(String.valueOf(mapApplication.get("id"))));
                application.setStatus(String.valueOf(mapApplication.get("status")));
                applications.add(application);
            }
        }
        return applications;
    }


    @Override
    public void insert(Application application) throws SQLException {
        int program_id=application.getProgram_id();
        int student_id=application.getStudent_id();
        try(DBSession dbSession = new DBSession()){
            dbSession.execute(FOREIGN_KEY_CHECKS);
            dbSession.execute(INSERT_APPLICATION, Arrays.asList(program_id,student_id,application.getSop(),
                    INITIAL_APPLICATION_STATUS,0,""));
            dbSession.execute(INSERT_EDUCATION,Arrays.asList(student_id,application.getHighest_education(),
                    application.getGrades(), INITIAL_OUTCOME_TYPE,application.getStart_date(),application.getEnd_date()));
        }

    }
}
