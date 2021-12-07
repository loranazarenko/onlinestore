package com.epam.onlinestore.dao.connection;

import com.epam.onlinestore.web.command.LoginCommand;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static DBManager dbManager;
    private static String connectionURL = "";
    private static final Connection connection = getConnection();

    public static DBManager getInstance() {

        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static Connection getConnection(String connectionUrl) {

        dbManager = DBManager.getInstance();
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl);
            con.setAutoCommit(true);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static Connection getConnection() {
        /*try (FileInputStream fis = new FileInputStream("dbConnection.properties")) {////src/main/resources/
            Properties properties = new Properties();
            properties.load(fis);
            connectionURL = (String) properties.get("db.url");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/
        connectionURL = "jdbc:mysql://localhost:3306/onlineStore?user=root&password=root";
        return getConnection(connectionURL);
    }

   /* public static void getStart() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = "DROP table IF EXISTS users; DROP table IF EXISTS teams; " +
                    "DROP table IF EXISTS users_teams;\n " +
                    "CREATE TABLE IF NOT EXISTS users (\n" +
                    " id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,\n" +
                    " login VARCHAR(10) UNIQUE); \n" +
                    "CREATE TABLE IF NOT EXISTS teams (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,\n" +
                    " name VARCHAR(10)); \n" +
                    " CREATE TABLE IF NOT EXISTS users_teams ( \n" +
                    " user_id INT REFERENCES users(id) ON DELETE CASCADE,\n" +
                    " team_id INT REFERENCES teams(id) ON DELETE CASCADE,\n" +
                    " UNIQUE (user_id, team_id));";

            statement.executeUpdate(sql);
        }


    }

    public void insertUser(User user) {
        ResultSet keys = null;
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.INSERT_USER,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, user.getLogin());
            if (st.executeUpdate() > 0) {
                keys = st.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setId(id);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (keys != null) {
                try {
                    keys.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public void insertTeam(Team team) {
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.INSERT_TEAM,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, team.getName());
            st.executeUpdate();
            try (ResultSet keys = st.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    team.setId(id);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        ResultSet rs = null;
        try (Statement st = connection.createStatement()) {
            rs = st.executeQuery(com.epam.rd.java.basic.practice8.db.DbConst.GET_ALL_USERS);
            while (rs.next()) {
                User user = User.createUser(rs.getString("login"));
                user.setId(rs.getInt("id"));
                if (!users.contains(user)) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return users;
    }

    public List<Team> findAllTeams() {
        List<Team> teams = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(com.epam.rd.java.basic.practice8.db.DbConst.GET_ALL_TEAMS)) {
            while (rs.next()) {
                Team team = Team.createTeam(rs.getString("name"));
                team.setId(rs.getInt("id"));
                if (!teams.contains(team)) {
                    teams.add(team);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return teams;
    }

    public User getUser(String login) {
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.FIND_USER_BY_LOGIN)) {
            st.setString(1, login);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    User user = User.createUser(rs.getString("login"));
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Team getTeam(String name) {
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.FIND_TEAM_BY_NAME)) {
            st.setString(1, name);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Team team = Team.createTeam(rs.getString("name"));
                    team.setId(rs.getInt("id"));
                    return team;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void setTeamsForUser(User user, Team... teams) throws SQLException {
        connection.setAutoCommit(false);
        if (user == null) {
            return;
        }
        int id = user.getId();
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.SQL_DELETE_ALL_TEAMS_FOR_USER)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        for (Team team : teams) {
            try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.INSERT_USERS_TEAMS)) {
                st.setInt(1, id);
                st.setInt(2, team.getId());
                st.executeUpdate();
                connection.commit();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        connection.setAutoCommit(true);
    }

    public List<Team> getUserTeams(User user) {
        List<Team> teams = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.FIND_TEAMS_BY_LOGIN)) {
            st.setString(1, user.getLogin());
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Team team = Team.createTeam(rs.getString("name"));
                    team.setId(rs.getInt("id"));
                    if (!teams.contains(team)) {
                        teams.add(team);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return teams;
    }

    public void deleteTeam(Team team) {
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.DELETE_TEAM)) {
            st.setString(1, team.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateTeam(Team team) {
        try (PreparedStatement st = connection.prepareStatement(com.epam.rd.java.basic.practice8.db.DbConst.UPDATE_TEAM)) {
            st.setString(1, team.getName());
            st.setInt(2, team.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/
}
