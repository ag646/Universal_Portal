package com.dal.universityPortal.database;

import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserStatus;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dal.universityPortal.database.query.UserQuery.*;

@Component
public class UserDao implements InsertDao<User>,UpdateDao<User> {

    public User fetchOne(String username) throws SQLException {
        Map<String, Object> row;
        User user = null;
        try (DBSession dbSession = new DBSession()) {
            List<Map<String, Object>> rows = dbSession.fetch(FETCH_USER_USING_USERNAME, Arrays.asList(username));
            if (rows.size() > 0) {
                row = rows.get(0);
                user = new User(row);
            }
        }
        return user;
    }

    @Override
    public void insert(User user) throws SQLException {
        try(DBSession dbSession = new DBSession()) {
            dbSession.execute(INSERT_USER_QUERY, Arrays.asList(user.getUsername(), user.getEmail(),
                    user.getPassword(), user.getType(), user.getStatusString()));
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try(DBSession dbSession = new DBSession()) {
            dbSession.execute(UPDATE_USER_QUERY, Arrays.asList(user.getUsername(), user.getEmail(),
                    user.getPassword(), user.getType(), user.getStatusString(), user.getId()));
        }
    }

    public List<User> fetchAllPendingUsers() throws SQLException {
        List<User> pendingUserList = new ArrayList<>();
        try (DBSession dbSession = new DBSession()) {
            List<Map<String, Object>> pendingUsers = dbSession.fetch(FETCH_PENDING_USERS_QUERY, Arrays.asList(
                UserStatus.PENDING.toString()));
            for (Map<String, Object> user : pendingUsers) {
                User pendingUser = new User(user);
                pendingUserList.add(pendingUser);
            }
        }
        return pendingUserList;
    }

    public void setUserStatus(User user) throws SQLException {
        try (DBSession dbSession = new DBSession()) {
            dbSession.execute(UPDATE_USER_STATUS, Arrays.asList(user.getStatusString(), user.getId()));
        }
    }

    public void setResetCode(User user, Integer resetCode) throws SQLException {
        try(DBSession dbSession = new DBSession()) {
            dbSession.execute(SET_USER_RESET_CODE, Arrays.asList(resetCode, user.getId()));
        }
    }
}
