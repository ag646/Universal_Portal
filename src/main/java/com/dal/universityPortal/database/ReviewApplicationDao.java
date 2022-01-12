package com.dal.universityPortal.database;

import com.dal.universityPortal.database.query.ApplicationQuery;
import com.dal.universityPortal.model.Application;
import org.springframework.stereotype.Component;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.database.query.ApplicationQuery.FETCH_ALL_APPLICATION;
import static com.dal.universityPortal.database.query.ApplicationQuery.FETCH_APPLICATION_BY_ID;

@Component
public class ReviewApplicationDao implements SelectDao<Application>,UpdateDao<Application> {

    @Override
    public List<Application> fetchAll() throws SQLException {
        List<Map<String, Object>> applicationList;
        List<Application> applications = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            applicationList = dbSession.fetch(ApplicationQuery.FETCH_ALL_APPLICATION);
            for (Map<String, Object> mapApplication : applicationList) {
                if(mapApplication.get("status").equals("In-process") || mapApplication.get("status").equals("New")) {
                    Application application = new Application();
                    application.setApplication_id(Integer.parseInt(String.valueOf(mapApplication.get("id"))));
                    application.setStatus(String.valueOf(mapApplication.get("status")));
                    applications.add(application);
                }
            }
        }
        return applications;
    }
    public Application fetchAllByParam(int id) throws SQLException {
        List<Map<String, Object>> applicationlist;
        Application application = new Application();
        try (DBSession dbSession = new DBSession()) {
            applicationlist=dbSession.fetch(ApplicationQuery.FETCH_APPLICATION_BY_ID_QUERY,Arrays.asList(id));

            for (Map<String, Object> applist: applicationlist){
                List<Map<String, Object>> student = dbSession.fetch(
                        ApplicationQuery.FETCH_STUDENT_BY_ID_QUERY,
                        Arrays.asList(applist.get("student_id")));
                List<Map<String, Object>> user = dbSession.fetch(
                        ApplicationQuery.FETCH_USER_BY_ID_QUERY,
                        Arrays.asList(applist.get("student_id")));
                List<Map<String, Object>> education = dbSession.fetch(
                        ApplicationQuery.FETCH_EDUCATION_BY_STUDENT_ID,
                        Arrays.asList(applist.get("student_id")));

                application.setApplication_id(Integer.parseInt(String.valueOf(applist.get("id"))));
                application.setProgram_id(Integer.parseInt(String.valueOf(applist.get("program_id"))));
                application.setStudent_id(Integer.parseInt(String.valueOf(applist.get("student_id"))));
                application.setSop(String.valueOf(applist.get("sop")));
                application.setStatus(String.valueOf(applist.get("status")));
                application.setProcessed_by(Integer.parseInt(String.valueOf(applist.get("processed_by"))));
                application.setComment(String.valueOf(applist.get("comment")));

                application.setFirst_name(String.valueOf(student.get(0).get("first_name")));
                application.setLast_name(String.valueOf(student.get(0).get("last_name")));
                application.setAddress(String.valueOf(student.get(0).get("address")));
                application.setMobile_number(String.valueOf(student.get(0).get("mobile_number")));
                application.setEmail_id(String.valueOf(user.get(0).get("email")));

                application.setHighest_education(String.valueOf(education.get(0).get("name")));
                application.setGrades(String.valueOf(education.get(0).get("outcome")));
                application.setStart_date(String.valueOf(education.get(0).get("start_date")));
                application.setEnd_date(String.valueOf(education.get(0).get("end_date")));
            }
        }
        return application;
    }

    @Override
    public void update(Application application) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(ApplicationQuery.UPDATE_APPLICATION_BY_APPLICATION_ID,
                    Arrays.asList(application.getStatus(),application.getProcessed_by(),
                            application.getComment(),application.getApplication_id()));
        }
    }
}
