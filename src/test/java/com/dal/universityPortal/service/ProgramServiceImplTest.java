package com.dal.universityPortal.service;

import com.dal.universityPortal.database.ProgramDao;
import com.dal.universityPortal.model.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;


public class ProgramServiceImplTest {
    private static final int program_id = 1;
    private static final String name = "MACS";
    private static final int university_id = 1;

    @Mock
    ProgramDao programDao;

    @Mock
    Program program = new Program(program_id, name, university_id);

    @Mock
    Program programTwoParam = new Program(name,university_id);

    @InjectMocks
    ProgramServiceImpl programService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveProgram() throws SQLException {
        programService.saveProgram(program);
        Mockito.verify(programDao).insert(program);
    }

    @Test
    void readProgram() throws SQLException {
        programService.readProgram(program_id);
        Mockito.verify(programDao).fetchAllByUniversityId(program_id);
    }

    @Test
    void deleteProgram() throws SQLException {
        programService.deleteProgram(programTwoParam);
        Mockito.verify(programDao).delete(programTwoParam);
    }
}
