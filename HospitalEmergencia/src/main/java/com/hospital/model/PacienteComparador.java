package com.hospital.model;

import java.util.Comparator;

public class PacienteComparador
        implements Comparator<Paciente> {

    @Override
    public int compare(Paciente p1, Paciente p2) {

        int prioridad =
                p1.getPrioridad().compareTo(
                        p2.getPrioridad()
                );

        if (prioridad == 0) {

            return p1.getHoraIngreso()
                    .compareTo(
                            p2.getHoraIngreso()
                    );
        }

        return prioridad;
    }
}
