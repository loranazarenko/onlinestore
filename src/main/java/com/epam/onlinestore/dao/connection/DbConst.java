package com.epam.onlinestore.dao.connection;

public abstract class DbConst {

    public static final String FIND_USER_BY_LOGIN = "SELECT id, login FROM users WHERE login = ?;";
    public static final String FIND_TEAM_BY_NAME = "SELECT id, name FROM teams WHERE name = ?;";
    public static final String FIND_TEAMS_BY_LOGIN = "SELECT teams.id, teams.name FROM users_teams" +
            " JOIN teams ON users_teams.team_id = teams.id" +
            " JOIN users ON users_teams.user_id = users.id" +
            " WHERE users.login = ?;";

    public static final String INSERT_USER = "INSERT INTO users (login) VALUES (?);";
    public static final String INSERT_TEAM = "INSERT INTO teams (name) VALUES (?);";
    public static final String INSERT_USERS_TEAMS = "INSERT INTO users_teams (user_id, team_id) VALUES (?,?);";
    public static final String GET_ALL_USERS = "SELECT id, login FROM users ORDER BY id;";
    public static final String GET_ALL_TEAMS = "SELECT id, name FROM teams ORDER BY id;";

    public static final String DELETE_TEAM = "DELETE FROM teams WHERE name = ?;";
    public static final String UPDATE_TEAM = "UPDATE teams SET name = ? WHERE id = ?;";

    public static final String SQL_DELETE_ALL_TEAMS_FOR_USER = "DELETE FROM users_teams WHERE user_id=?;";

    private DbConst() {
    }

}
