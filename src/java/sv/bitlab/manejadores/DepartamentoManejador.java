/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package sv.bitlab.manejadores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.bitlab.controladores.DepartamentoControlador;
import sv.bitlab.controladores.EstadoDepartamentoControlador;
import sv.bitlab.entidades.Departamento;
import sv.bitlab.entidades.EstadoDepartamento;
import sv.bitlab.utilidades.UtilidadesManejador;

/**
 *
 * @author Manuel
 */
@ManagedBean
@ViewScoped
public class DepartamentoManejador extends ManejadorAbstracto<Departamento> {

    private Departamento departamento = new Departamento();
    private EstadoDepartamento estadoDepartamento;
    private DepartamentoControlador departamentoControlador = new DepartamentoControlador();
    private EstadoDepartamentoControlador estadoDepartamentoControlador = new EstadoDepartamentoControlador();
    private List<EstadoDepartamento> estadoDepList;
    private List<Departamento> departamentoList;
    private boolean isNew = true;

    public DepartamentoManejador() {
        super(Departamento.class);
        departamentoControlador = new DepartamentoControlador();
    }

    @PostConstruct
    public void cargarInfo() {
        departamento = new Departamento();
        estadoDepartamento = new EstadoDepartamento();
        estadoDepList = estadoDepartamentoControlador.encontrarEntidades();
        departamentoList = departamentoControlador.encontrarEntidades();
    }

    public void guardarDepartamento() {
        try {
            departamentoControlador.editar(departamento);
            UtilidadesManejador.lanzarInfo("Exitoso", "Departamento Guardado");
        } catch (Exception ex) {
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void nuevaEntidad() throws NoSuchMethodException {
        reset();
        super.nuevaEntidad();
    }

    public void reset() {
        departamento = new Departamento();
        estadoDepartamento = new EstadoDepartamento();
        isNew = true;
    }

    @Override
    public DepartamentoControlador getControlador() {
        return departamentoControlador;
    }

    public List<EstadoDepartamento> getEstadoDepList() {
        return estadoDepList;
    }

    public void setEstadoDepList(List<EstadoDepartamento> estadoDepList) {
        this.estadoDepList = estadoDepList;
    }

    public DepartamentoControlador getDepartamentoControlador() {
        return departamentoControlador;
    }

    public void setDepartamentoControlador(DepartamentoControlador departamentoControlador) {
        this.departamentoControlador = departamentoControlador;
    }

    public EstadoDepartamentoControlador getEstadoDepartamentoControlador() {
        return estadoDepartamentoControlador;
    }

    public void setEstadoDepartamentoControlador(EstadoDepartamentoControlador estadoDepartamentoControlador) {
        this.estadoDepartamentoControlador = estadoDepartamentoControlador;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public EstadoDepartamento getEstadoDepartamento() {
        return estadoDepartamento;
    }

    public void setEstadoDepartamento(EstadoDepartamento estadoDepartamento) {
        this.estadoDepartamento = estadoDepartamento;
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

}
