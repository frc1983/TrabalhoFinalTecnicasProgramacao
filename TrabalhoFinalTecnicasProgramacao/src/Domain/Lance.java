package Domain;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;
import java.text.NumberFormat;

public class Lance {

    private Integer id;
    private Date data;
    private Time hora;
    private BigDecimal valor;
    private Lote lote;
    private Usuario usuario;

    public Lance() {
    }

    public Lance(Integer id, Date data, Time hora, BigDecimal valor, Lote lote, Usuario usuario) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.lote = lote;
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date datahora) {
        this.data = datahora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote idlote) {
        this.lote = idlote;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(this.valor);
    }
}
