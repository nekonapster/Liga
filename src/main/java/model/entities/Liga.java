package model.entities;

import jakarta.persistence.*;

import java.util.Set;

import java.util.List;

@Entity
public class Liga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "liga")
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "liga")
    private List<Jornada> jornadas;

    // constructors, getters and setters


    public Liga(Long id, String nombre, List<Equipo> equipos, List<Jornada> jornadas) {
        this.id = id;
        this.nombre = nombre;
        this.equipos = equipos;
        this.jornadas = jornadas;
    }

    public Liga(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }
}
