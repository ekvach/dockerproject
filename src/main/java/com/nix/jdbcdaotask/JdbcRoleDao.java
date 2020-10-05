package com.nix.jdbcdaotask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

    private static final Logger logger = LoggerFactory.getLogger(JdbcRoleDao.class);

    public JdbcRoleDao() {
    }

    public JdbcRoleDao(String DB_DRIVER, String DB_URI) {
        super(DB_DRIVER, DB_URI);
    }

    @Override
    public void create(Role role) {
        if (role == null) {
            logger.error("cannot create a record from null reference",
                    new NullPointerException("cannot create a record from null reference"));
            throw new NullPointerException("cannot create a record from null reference");
        }

        String SQL = "INSERT INTO USER_ROLE VALUES (?, ?)";

        try (Connection connection = createConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setLong(1, role.getId());
                preparedStatement.setString(2, role.getName());
                preparedStatement.execute();

                connection.commit();
            } catch (Exception e) {
                if (connection != null) {
                    logger.error("something went wrong upon creating", e);
                    connection.rollback();
                }
            }
        } catch (Exception throwables) {
            logger.error("something went wrong upon creating", throwables);

        }
    }

    @Override
    public void update(Role role) {
        if (role == null) {
            logger.error("cannot update a record with null reference",
                    new NullPointerException("cannot update a record with null reference"));
            throw new NullPointerException("cannot update a record with null reference");
        }
        String SQL = "UPDATE USER_ROLE SET NAME = ? WHERE ID = ?";

        try (Connection connection = createConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setString(1, role.getName());
                preparedStatement.setLong(2, role.getId());
                preparedStatement.execute();

                connection.commit();
            } catch (Exception e) {
                logger.error("something went wrong upon updating", e);
                if (connection != null) {
                    connection.rollback();
                }
            }
        } catch (Exception throwables) {
            logger.error("something went wrong upon updating", throwables);
        }
    }

    @Override
    public void remove(Role role) {
        if (role == null) {
            logger.error("cannot refer to null",
                    new NullPointerException("cannot refer to null"));
            throw new NullPointerException("cannot refer to null");
        }

        String SQL = "DELETE FROM USER_ROLE WHERE ID = ?";

        try (Connection connection = createConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
                preparedStatement.setLong(1, role.getId());
                preparedStatement.execute();

                connection.commit();
            } catch (Exception e) {
                logger.error("something went wrong upon removing", e);
                if (connection != null) {
                    connection.rollback();
                }
            }
        } catch (Exception throwables) {
            logger.error("something went wrong upon removing", throwables);
        }
    }

    @Override
    public Role findByName(String name) {
        if (name == null) {
            logger.error("cannot parse null name",
                    new NullPointerException("cannot parse null name"));
            throw new NullPointerException("cannot parse null name");
        }

        Role role = null;
        String SQL = "SELECT * FROM USER_ROLE WHERE NAME = ?";

        try (Connection connection = createConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = new Role();
                role.setName(resultSet.getString("NAME"));
                role.setId(resultSet.getLong("ID"));
            }

        } catch (Exception throwables) {
            logger.error("something went wrong with search by name", throwables);
        }
        return role;
    }

}
