
package com.hospital.controller;

import com.hospital.model.Paciente;
import com.hospital.model.Prioridad;
import com.hospital.dao.PacienteDAO;
import com.hospital.service.ColaPacientes;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TextArea areaPacientes;

    private ColaPacientes cola =
            new ColaPacientes();

    private PacienteDAO dao =
            new PacienteDAO();

    @FXML
    public void initialize() {

        comboPrioridad.getItems().addAll(
                Prioridad.CRITICAL,
                Prioridad.HIGH,
                Prioridad.MEDIUM,
                Prioridad.LOW
        );
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

        actualizarLista();
    }

    @FXML
    public void atenderPaciente() {

        Paciente p =
                cola.atenderPaciente();

        if (p != null) {

            areaPacientes.appendText(
                    "\nAtendido: "
                            + p.getNombre()
            );
        }

        actualizarLista();
    }

    private void actualizarLista() {

        areaPacientes.clear();

        for (Paciente p : cola.getCola()) {

            areaPacientes.appendText(
                    p.getNombre()
                            + " - "
                            + p.getPrioridad()
                            + "\n"
            );
        }
    }
}
