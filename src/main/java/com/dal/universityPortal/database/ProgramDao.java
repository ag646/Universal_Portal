package com.dal.universityPortal.database;

import com.dal.universityPortal.database.query.PaymentQuery;
import com.dal.universityPortal.database.query.ProgramQuery;
import com.dal.universityPortal.model.Program;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.database.query.ProgramQuery.*;

@Component
public class ProgramDao implements SelectDao<Program>,InsertDao<Program>,DeleteDao<Program> {


    @Override
    public List<Program> fetchAll() throws SQLException {
        List<Map<String, Object>> programList;
        List<Program> programs = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            programList = dbSession.fetch(FETCH_ALL_PROGRAMS);
            for (Map<String, Object> mapProgram : programList) {
                Program program = new Program();
                program.setAmount(Integer.parseInt(String.valueOf(mapProgram.get("amount"))));
                programs.add(program);
            }
        }
        return programs;
    }

    public List<Program> fetchAllByUniversityId(int id) throws SQLException {
        List<Map<String, Object>> programList;
        List<Program> programs = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            programList = dbSession.fetch(ProgramQuery.FETCH_PROGRAMS_BY_UNIVERSITY_ID, Arrays.asList(id));
            for (Map<String, Object> mapProgram : programList) {
                Program program = new Program();
                program.setId(Integer.parseInt(String.valueOf(mapProgram.get("id"))));
                program.setUniversityId(Integer.parseInt(String.valueOf(mapProgram.get("university_id"))));
                program.setName(String.valueOf(mapProgram.get("name")));
                programs.add(program);
            }
        }
        return programs;
    }

    public Program fetchAllByApplicationId(int id) throws SQLException {
        List<Map<String, Object>> programList;
        Program program = new Program();
        try (DBSession dbSession = new DBSession()) {
            programList = dbSession.fetch(FETCH_PROGRAMS_BY_APPLICATION_ID, Arrays.asList(id));
            for (Map<String, Object> mapProgram : programList) {
                program.setId(Integer.parseInt(String.valueOf(mapProgram.get("id"))));
                program.setUniversityId(Integer.parseInt(String.valueOf(mapProgram.get("university_id"))));
                program.setName(String.valueOf(mapProgram.get("name")));
            }
        }
        return program;
    }


    @Override
    public void insert(Program program) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(PaymentQuery.FOREIGN_KEY_CHECKS);
            dbSession.setAutoCommit(false);
            dbSession.execute(ProgramQuery.INSERT_INTO_PROGRAM, Arrays.asList(program.getName(),
                    program.getUniversityId()));
            dbSession.setAutoCommit(true);
        }
    }

    @Override
    public void delete(Program program1) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(ProgramQuery.DELETE_PROGRAM,Arrays.asList(program1.getUniversityId()+" AND name="+"'"+program1.getName()+"'"));
        }
    }
}
