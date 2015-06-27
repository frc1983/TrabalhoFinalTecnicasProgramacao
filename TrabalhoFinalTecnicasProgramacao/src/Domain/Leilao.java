package Domain;

import java.sql.Time;
import java.sql.Date;

public class Leilao {
    
    private Integer id;
    private Date datainicio;
    private Date datatermino;
    private Time horainicio;
    private Time horatermino;
    private FormaLance formalance;
    private Lote lote;
    private Natureza natureza;
    private Usuario usuario;

    public Leilao() {
    }

    public Leilao(Integer id, Usuario usuario, FormaLance formaLance, Lote lote, Natureza natureza, Date dataInicio, Date dataTermino, Time horaInicio, Time horaTermino) {
        this.id = id;
        this.usuario = usuario;
        this.formalance = formaLance;
        this.lote = lote;
        this.natureza = natureza;
        this.datainicio = dataInicio;
        this.datatermino = dataTermino;
        this.horainicio = horaInicio;
        this.horatermino = horaTermino;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatatermino() {
        return datatermino;
    }

    public void setDatatermino(Date datatermino) {
        this.datatermino = datatermino;
    }

    public FormaLance getFormalance() {
        return formalance;
    }

    public void setFormalance(FormaLance formalance) {
        this.formalance = formalance;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Time getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public Time getHoratermino() {
        return horatermino;
    }

    public void setHoratermino(Time horatermino) {
        this.horatermino = horatermino;
    }
}
