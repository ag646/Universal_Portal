package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ProgramDao;
import com.dal.universityPortal.model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService{
    Logger logger = LogManager.getLogger(ProgramServiceImpl.class);

    @Autowired
    private ProgramDao programDao;

    @Override
    public Boolean saveProgram(Program program) {
        try {
            programDao.insert(program);
        } catch (SQLException exception) {
            logger.error(exception);
        }
        return true;
    }

    @Override
    public List<Program> readProgram(int id) throws SQLException {
        return programDao.fetchAllByUniversityId(id);
    }

    @Override
    public void deleteProgram(Program program) throws SQLException {
        programDao.delete(program);
    }
}
