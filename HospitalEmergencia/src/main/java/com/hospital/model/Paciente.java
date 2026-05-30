package com.hospital.model;

import java.time.LocalDateTime;

public class Paciente {

    private int id;
    private String nombre;
    private int edad;
    private String dpi;
    private String sintomas;
    private Prioridad prioridad;
    private LocalDateTime horaIngreso;
    private boolean atendido;

    public Paciente(
            String nombre,
            int edad,
            String dpi,
            String sintomas,
            Prioridad prioridad
    ) {

        this.nombre = nombre;
        this.edad = edad;
        this.dpi = dpi;
        this.sintomas = sintomas;
        this.prioridad = prioridad;
        this.horaIngreso = LocalDateTime.now();
        this.atendido = false;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDpi() {
        return dpi;
    }

    public String getSintomas() {
        return sintomas;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
}
