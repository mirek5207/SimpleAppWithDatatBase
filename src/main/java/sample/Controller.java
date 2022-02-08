package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class Controller {
    @FXML
    private TextArea displaySurname;
    @FXML
    private TextArea displayId;
    @FXML
    private TextArea displayName;

    @FXML
    private Button Add_button;

    @FXML
    private Button Display_button;

    @FXML
    private Button Delete_button;

    @FXML
    private TextField Add_First_name;

    @FXML
    private TextField Add_Last_name;

    @FXML
    private TextField Delete_Id;

    @FXML
    private Button Search_button;

    @FXML
    private TextField Search_First_name;

    @FXML
    private TextField Search_Last_name;

    @FXML
    private Button Update_button;

    @FXML
    private TextField Update_Id;

    @FXML
    private TextField Update_First_name;

    @FXML
    private TextField Update_Last_name;


    public void HandleButton_Add(javafx.event.ActionEvent actionEvent) {

        DataBase dataBase = new DataBase();
        dataBase.addData("STUDENTS", Add_Last_name.getText(), Add_First_name.getText());
        HandleButton_Display_DataBase(actionEvent);
        dataBase.closeConnection();
    }


    public void HandleButton_Delete(ActionEvent actionEvent) {
        DataBase dataBase = new DataBase();
        dataBase.deleteData("STUDENTS", Delete_Id.getText());
        HandleButton_Display_DataBase(actionEvent);
        dataBase.closeConnection();
    }

    public void HandleButton_Display_DataBase(ActionEvent actionEvent) {
        displaySurname.clear();
        displayId.clear();
        displayName.clear();
        DataBase dataBase = new DataBase();
        List<Student> lista = dataBase.getData("STUDENTS");

        for (Student s : lista) {
            displaySurname.setText(displaySurname.getText() + s.getsurName() + System.lineSeparator());
            displayId.setText(displayId.getText() + s.getId() + System.lineSeparator());
            displayName.setText(displayName.getText() + s.getname() + System.lineSeparator());
        }
        dataBase.closeConnection();
    }

    public void HandleButton_Update(ActionEvent actionEvent) {
        DataBase dataBase = new DataBase();
        dataBase.updateData("STUDENTS", Update_Id.getText(), Update_Last_name.getText(), Update_First_name.getText());
        HandleButton_Display_DataBase(actionEvent);
        dataBase.closeConnection();
    }

    public void HandleButton_Search(ActionEvent actionEvent) {
        DataBase dataBase = new DataBase();
        List<Student> students = dataBase.searchData("STUDENTS", Search_First_name.getText());
        displaySurname.clear();
        displayId.clear();
        displayName.clear();
        for (Student s : students) {
            displaySurname.setText(displaySurname.getText() + s.getsurName() + System.lineSeparator());
            displayId.setText(displayId.getText() + s.getId() + System.lineSeparator());
            displayName.setText(displayName.getText() + s.getname() + System.lineSeparator());
        }
        dataBase.closeConnection();
    }

    public void HandleButton_Exit(ActionEvent actionEvent) {

        Platform.exit();
    }
}
