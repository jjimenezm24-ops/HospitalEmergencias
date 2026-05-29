//Clase que agrega pacientes y los quita de la cola
package com.hospital.service;

import com.hospital.model.*;
import java.util.PriorityQueue;
public class ColaPacientes {
     private PriorityQueue<Paciente> cola;

    public ColaPacientes() {

        cola = new PriorityQueue<>(
                new PacienteComparador()
        );
    }

    public void agregarPaciente(Paciente p) {

        cola.add(p);
    }

    public Paciente atenderPaciente() {

        return cola.poll();
    }

    public PriorityQueue<Paciente> getCola() {

        return cola;
    }
}
