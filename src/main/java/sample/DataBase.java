package sample;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * @author komp
 */
public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:mojaPierwszaBaza.db";
    private Connection connection;
    private Statement statement;

    public DataBase() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            createTable();
        } catch (SQLException ex) {
            System.err.println("Problem z otwarciem połączenia");
        }
    }

    public boolean createTable() {
        String tworz = "CREATE TABLE IF NOT EXISTS STUDENTS(id INTEGER PRIMARY KEY AUTOINCREMENT, surName String, name String)";
        try {
            statement.execute(tworz);
        } catch (SQLException e) {
            System.err.println("Błąd przy tworzeniu tabeli");
            return false;
        }
        return true;
    }

    public boolean addData(String tabela, String surName, String name) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tabela + " VALUES (null,?,?);");
            preparedStatement.setString(1, surName);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + surName + " " + name);
            return false;
        }
        return true;
    }

    public boolean deleteData(String tabela, String id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Delete FROM " + tabela + " Where id = " + Integer.parseInt(id) + ";");
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy usuwaniu danych studenta nr: " + id);
            return false;
        }
        return true;
    }

    public List<Student> getData(String tabelName) {
        List<Student> data = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tabelName);
            int id;
            String surName, name;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                surName = resultSet.getString("surName");
                name = resultSet.getString("name");
                data.add(new Student(id, surName, name));
            }
        } catch (SQLException e) {
            System.err.println("Problem z wczytaniem danych z BD");
            return null;
        }
        return data;
    }

    public List<Student> searchData(String tabelName, String text) {
        List<Student> data = new LinkedList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM " + tabelName + " Where lower(name) like ? or lower(surName) like ?");
            preparedStatement.setString(1, "%"+text.toLowerCase()+"%");
            preparedStatement.setString(2, "%"+text.toLowerCase()+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            int currentRowId;
            String currentRowSurName, currentRowName;
            while (resultSet.next()) {
                currentRowId = resultSet.getInt("id");
                currentRowSurName = resultSet.getString("surName");
                currentRowName = resultSet.getString("name");
                data.add(new Student(currentRowId, currentRowSurName, currentRowName));
            }
        } catch (SQLException e) {
            System.err.println("Problem z wczytaniem danych z BD");
            return null;
        }
        return data;
    }

    public boolean updateData(String tabelName, String id, String surName, String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Update " + tabelName + " Set surName = ? , name = ? Where id = ?");
            preparedStatement.setString(1, surName);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, Integer.parseInt(id));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + surName + " " + name);
            System.err.println("Błąd przy wprowadzaniu danych studenta: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknięciem połączenia");
        }
    }
}