package com.dal.universityPortal.service;

import com.dal.universityPortal.model.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchUniversityServiceImplTest {

    private static final String program1Name = "MACS";
    private static final Integer program1Id = 1;
    private static final String program2Name = "MCS";
    private static final Integer program2Id = 2;
    private static final Integer userId = 3;
    private static final Integer university1Id = 10;
    private static final String universityName = "Dalhousie University";
    private static final String universityDescription = "University in Halifax";

    private SearchUniversityServiceImpl searchUniversityServiceImpl;

    @BeforeEach
    public void setup(){
        searchUniversityServiceImpl = Mockito.mock(SearchUniversityServiceImpl.class);
    }

    @Test
    void getUniversityDetailsTest() throws SQLException {
        Program program = new Program(program1Id, program1Name, university1Id);
        Program universityDetail = new Program();
        universityDetail.setUserId(userId);
        universityDetail.setUniversityName(universityName);
        universityDetail.setUniversityDescription(universityDescription);
        Program universityDetails = universityDetail;
        Mockito.doReturn(universityDetails).when(searchUniversityServiceImpl).getUniversityDetails(program);
        assertEquals(universityDetails, universityDetail);
    }

    @Test
    void getProgramDetailsTest() throws SQLException {
        Program program1 = new Program(program1Id, program1Name, university1Id);
        Program program2 = new Program(program2Id, program2Name, university1Id);
        ArrayList<Program> programs = new ArrayList<>(Arrays.asList(program1, program2));
        Mockito.doReturn(programs).when(searchUniversityServiceImpl).getProgramDetails(program1.getUniversityId());
        assertEquals(2, programs.size());
    }
}
