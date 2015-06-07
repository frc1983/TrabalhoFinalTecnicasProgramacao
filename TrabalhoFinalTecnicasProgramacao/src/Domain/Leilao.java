package Domain;

import java.util.Date;

public class Leilao {
    
    private Integer id;
    private Date datainicio;
    private Date datatermino;
    private FormaLance idformalance;
    private Lote idlote;
    private Natureza idnatureza;
    private Usuario idusuario;

    public Leilao() {
    }

    public Leilao(Integer id) {
        this.id = id;
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

    public FormaLance getIdformalance() {
        return idformalance;
    }

    public void setIdformalance(FormaLance idformalance) {
        this.idformalance = idformalance;
    }

    public Lote getIdlote() {
        return idlote;
    }

    public void setIdlote(Lote idlote) {
        this.idlote = idlote;
    }

    public Natureza getIdnatureza() {
        return idnatureza;
    }

    public void setIdnatureza(Natureza idnatureza) {
        this.idnatureza = idnatureza;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }
}
