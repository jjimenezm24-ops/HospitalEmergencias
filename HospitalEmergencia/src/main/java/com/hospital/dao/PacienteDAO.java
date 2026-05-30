package com.hospital.dao;

import com.hospital.model.Paciente;
import com.hospital.model.Prioridad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

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

            System.out.println(
                    e.getMessage()
            );
        }
    }

    public List<Paciente> obtenerPacientes() {

        List<Paciente> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM pacientes";

        try {

            Connection con =
                    Conexion.conectar();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while (rs.next()) {

                Paciente p =
                        new Paciente(
                                rs.getString("nombre"),
                                rs.getInt("edad"),
                                rs.getString("dpi"),
                                rs.getString("sintomas"),
                                Prioridad.valueOf(
                                        rs.getString("prioridad")
                                )
                        );

                p.setAtendido(
                        rs.getBoolean("atendido")
                );

                lista.add(p);
            }

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

        return lista;
    }

    public void marcarAtendido(
            String dpi
    ) {

        String sql =
                """
                UPDATE pacientes
                SET atendido = true
                WHERE dpi = ?
                """;

        try {

            Connection con =
                    Conexion.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    dpi
            );

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}
