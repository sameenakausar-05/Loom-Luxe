package com.loomandluxe.dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.loomandluxe.dao.UserDAO;
import com.loomandluxe.model.User;
import com.loomandluxe.util.DBConnection;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User user) {

        String query = "INSERT INTO users (full_name, email, phone, password, "
                + "address_line1, address_line2, city, state, country, pincode) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddressLine1());
            preparedStatement.setString(6, user.getAddressLine2());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getState());
            preparedStatement.setString(9, user.getCountry());
            preparedStatement.setString(10, user.getPincode());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User loginUser(String email, String password) {

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setCountry(resultSet.getString("country"));
                user.setPincode(resultSet.getString("pincode"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserById(int userId) {

        String query = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setCountry(resultSet.getString("country"));
                user.setPincode(resultSet.getString("pincode"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setCountry(resultSet.getString("country"));
                user.setPincode(resultSet.getString("pincode"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByPhone(String phone) {

        String query = "SELECT * FROM users WHERE phone = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setCountry(resultSet.getString("country"));
                user.setPincode(resultSet.getString("pincode"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String query = "SELECT * FROM users";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                User user = new User();

                user.setUserId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setAddressLine1(resultSet.getString("address_line1"));
                user.setAddressLine2(resultSet.getString("address_line2"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setCountry(resultSet.getString("country"));
                user.setPincode(resultSet.getString("pincode"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean updateUser(User user) {

        String query = "UPDATE users SET full_name = ?, email = ?, phone = ?, "
                + "address_line1 = ?, address_line2 = ?, city = ?, state = ?, "
                + "country = ?, pincode = ? WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getAddressLine1());
            preparedStatement.setString(5, user.getAddressLine2());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, user.getState());
            preparedStatement.setString(8, user.getCountry());
            preparedStatement.setString(9, user.getPincode());
            preparedStatement.setInt(10, user.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updatePassword(int userId, String newPassword) {

        String query = "UPDATE users SET password = ? WHERE user_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isEmailExists(String email) {

        String query = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isPhoneExists(String phone) {

        String query = "SELECT COUNT(*) FROM users WHERE phone = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, phone);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean addUser(User user) {

        String query =
                "INSERT INTO users "
                + "(full_name, email, phone, password, "
                + "address_line1, address_line2, city, "
                + "state, country, pincode) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    connection.prepareStatement(query)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getPassword());

            ps.setString(5, user.getAddressLine1());
            ps.setString(6, user.getAddressLine2());

            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getCountry());
            ps.setString(10, user.getPincode());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    
}
