
package com.hospital.dao;

import com.hospital.model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
public class PacienteDAO {
    public void guardarPaciente(Paciente p) {

        String sql = """
                INSERT INTO pacientes
                (nombre, edad, dpi, sintomas, prioridad)
                VALUES (?, ?, ?, ?, ?)
                """;

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, p.getNombre());

            ps.setInt(2, p.getEdad());

            ps.setString(3, p.getDpi());

            ps.setString(4, p.getSintomas());

            ps.setString(
                    5,
                    p.getPrioridad().name()
            );

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
