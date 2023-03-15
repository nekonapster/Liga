package model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "liga_id")
    private Liga liga;

    @OneToMany(mappedBy = "jornada")
    private List<Partido> partidos;

    // constructors, getters and setters


    public Jornada(int numero, LocalDate fecha) {
        this.numero = numero;
        this.fecha = fecha;
    }

    public Jornada(Long id, int numero, LocalDate fecha, Liga liga, List<Partido> partidos) {
        this.id = id;
        this.numero = numero;
        this.fecha = fecha;
        this.liga = liga;
        this.partidos = partidos;
    }

    public Jornada() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }
}

