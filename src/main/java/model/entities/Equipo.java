package model.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int partidosJugados;

    private int partidosGanados;

    private int partidosPerdidos;

    private int puntosClasificacion;

    private int puntosAnotados;

    private int puntosEncajados;

    @OneToMany(mappedBy = "equipoLocal")
    private List<Partido> partidosLocal;

    @OneToMany(mappedBy = "equipoVisitante")
    private List<Partido> partidosVisitante;

    // constructors, getters and setters


    public Equipo(Long id, String nombre, int partidosJugados, int partidosGanados, int partidosPerdidos, int puntosClasificacion, int puntosAnotados, int puntosEncajados, List<Partido> partidosLocal, List<Partido> partidosVisitante) {
        this.id = id;
        this.nombre = nombre;
        this.partidosJugados = partidosJugados;
        this.partidosGanados = partidosGanados;
        this.partidosPerdidos = partidosPerdidos;
        this.puntosClasificacion = puntosClasificacion;
        this.puntosAnotados = puntosAnotados;
        this.puntosEncajados = puntosEncajados;
        this.partidosLocal = partidosLocal;
        this.partidosVisitante = partidosVisitante;
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo() {

    }

    public Equipo(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getPuntosClasificacion() {
        return puntosClasificacion;
    }

    public void setPuntosClasificacion(int puntosClasificacion) {
        this.puntosClasificacion = puntosClasificacion;
    }

    public int getPuntosAnotados() {
        return puntosAnotados;
    }

    public void setPuntosAnotados(int puntosAnotados) {
        this.puntosAnotados = puntosAnotados;
    }

    public int getPuntosEncajados() {
        return puntosEncajados;
    }

    public void setPuntosEncajados(int puntosEncajados) {
        this.puntosEncajados = puntosEncajados;
    }

    public List<Partido> getPartidosLocal() {
        return partidosLocal;
    }

    public void setPartidosLocal(List<Partido> partidosLocal) {
        this.partidosLocal = partidosLocal;
    }

    public List<Partido> getPartidosVisitante() {
        return partidosVisitante;
    }

    public void setPartidosVisitante(List<Partido> partidosVisitante) {
        this.partidosVisitante = partidosVisitante;
    }
}
