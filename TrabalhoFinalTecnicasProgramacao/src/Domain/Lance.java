package Domain;

import java.math.BigDecimal;
import java.util.Date;

public class Lance {
    
    private Integer id;
    private Date datahora;
    private BigDecimal valor;
    private Lote idlote;    
    private Usuario idusuario;

    public Lance() {
    }

    public Lance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Lote getIdlote() {
        return idlote;
    }

    public void setIdlote(Lote idlote) {
        this.idlote = idlote;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }
}
