/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sv.bitlab.manejadores;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sv.bitlab.controladores.TipoUsuarioControlador;
import sv.bitlab.controladores.UsuarioControlador;
import sv.bitlab.entidades.TipoUsuario;
import sv.bitlab.entidades.Usuario;
import sv.bitlab.utilidades.EncriptacionTexto;
/**
 *
 * @author Oscar
 */
@ManagedBean
@SessionScoped
public class UsuarioManejador extends ManejadorAbstracto<Usuario> {
    private static final Logger log = LoggerFactory.getLogger(UsuarioManejador.class);
    private Usuario usuario;
    private TipoUsuario tipoUsuario;
    private UsuarioControlador usuarioControlador = new UsuarioControlador();
    private TipoUsuarioControlador tipoUsuarioControlador = new TipoUsuarioControlador();
    private List<TipoUsuario> tipoUsuarioList;
    private boolean isNew = true;
    private String contrasena;
    public UsuarioManejador() {
        super(Usuario.class);
        usuarioControlador = new UsuarioControlador();
    }
    @PostConstruct
    public void cargarUsuarioList(){
        usuario = new Usuario();
        tipoUsuario = new TipoUsuario();
        tipoUsuarioList = tipoUsuarioControlador.encontrarEntidades();
    }
    //encriptando la contrasena de usuarios hacia la base de datos
    @Override
    public void guardarEntidad() {
        log.info("Guardando la identidad");
        if(getEntidadSeleccion().getUsrContrasena() == null || !getEntidadSeleccion().getUsrContrasena().equals(contrasena)){
            EncriptacionTexto encriptacionTexto = new  EncriptacionTexto();
            getEntidadSeleccion().setUsrContrasena(encriptacionTexto.getTextoEncriptado(contrasena));
        }
        super.guardarEntidad();
    }
    @Override
    public void nuevaEntidad() throws NoSuchMethodException {
        reset();
        super.nuevaEntidad(); 
    }
    public void completarInformacion(Usuario usr){
        log.info("ingresando a crear la identidad");
        setEntidadSeleccion(usr);
        this.contrasena = getEntidadSeleccion().getUsrContrasena();
    }
    public void validarAcceso(){
    }
    @Override
    public UsuarioControlador getControlador() {
        return usuarioControlador;
    }
    public UsuarioControlador getUsuarioControlador() {
        return usuarioControlador;
    }
    public void setUsuarioControlador(UsuarioControlador usuarioControlador) {
        this.usuarioControlador = usuarioControlador;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public TipoUsuarioControlador getTipoUsuarioControlador() {
        return tipoUsuarioControlador;
    }
    public void setTipoUsuarioControlador(TipoUsuarioControlador tipoUsuarioControlador) {
        this.tipoUsuarioControlador = tipoUsuarioControlador;
    }
    public List<TipoUsuario> getTipoUsuarioList() {
        return tipoUsuarioList;
    }
    public void setTipoUsuarioList(List<TipoUsuario> tipoUsuarioList) {
        this.tipoUsuarioList = tipoUsuarioList;
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public boolean isIsNew() {
        return isNew;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
    public void reset(){
        usuario = new Usuario();
        tipoUsuario = new TipoUsuario();
         isNew = true;
    }
}
