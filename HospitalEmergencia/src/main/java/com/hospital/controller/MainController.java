package com.hospital.controller;

import com.hospital.dao.PacienteDAO;
import com.hospital.model.*;
import com.hospital.service.ColaPacientes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtDpi;

    @FXML
    private TextArea txtSintomas;

    @FXML
    private ComboBox<Prioridad> comboPrioridad;

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> colNombre;

    @FXML
    private TableColumn<Paciente, String> colPrioridad;

    @FXML
    private TableColumn<Paciente, String> colHora;

    private ObservableList<Paciente> lista =
            FXCollections.observableArrayList();

    private ColaPacientes cola =
            new ColaPacientes();

    private PacienteDAO dao =
            new PacienteDAO();

    @FXML
    public void initialize() {

        comboPrioridad.getItems().addAll(
                Prioridad.Critico,
                Prioridad.Grave,
                Prioridad.Medio,
                Prioridad.Leve
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colPrioridad.setCellValueFactory(
                new PropertyValueFactory<>("prioridad")
        );

        colHora.setCellValueFactory(
                new PropertyValueFactory<>("horaIngreso")
        );

        tablaPacientes.setItems(lista);
    }

    @FXML
    public void registrarPaciente() {

        Paciente p = new Paciente(

                txtNombre.getText(),

                Integer.parseInt(
                        txtEdad.getText()
                ),

                txtDpi.getText(),

                txtSintomas.getText(),

                comboPrioridad.getValue()
        );

        cola.agregarPaciente(p);

        dao.guardarPaciente(p);

        lista.add(p);

        limpiarCampos();
    }

    @FXML
    public void atenderPaciente() {

        Paciente p =
                cola.atenderPaciente();

        if (p != null) {

            lista.remove(p);

            Alert alert =
                    new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Paciente");

            alert.setHeaderText(null);

            alert.setContentText(
                    "Atendido: "
                            + p.getNombre()
            );

            alert.showAndWait();
        }
    }

    private void limpiarCampos() {

        txtNombre.clear();

        txtEdad.clear();

        txtDpi.clear();

        txtSintomas.clear();

        comboPrioridad.setValue(null);
    }
}
