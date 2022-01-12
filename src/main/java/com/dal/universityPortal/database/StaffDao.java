package com.dal.universityPortal.database;

import com.dal.universityPortal.model.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Arrays;

import static com.dal.universityPortal.database.query.UserQuery.MAP_STAFF_USER_WITH_UNIVERSITY_USER;

@Component
public class StaffDao extends UserDao {

    public void mapUniversity(User user, Integer universityId) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            Integer staffId = this.fetchOne(user.getUsername()).getId();
            dbSession.execute(MAP_STAFF_USER_WITH_UNIVERSITY_USER, Arrays.asList(staffId, universityId));
        }
    }
}
