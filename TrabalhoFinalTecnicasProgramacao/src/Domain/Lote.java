package Domain;

import java.math.BigDecimal;
import java.util.Collection;

public class Lote {
    
    private Integer id;
    private BigDecimal preco;
    private Leilao leilao;
    private Collection<Lance> lances;
    private Collection<Bem> bens;

    public Lote() {
    }

    public Lote(Integer id) {
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public BigDecimal getPreco() {
        return preco;
    }
    
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    
    public Leilao getLeilao() {
        return leilao;
    }
    
    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }
    
    public Collection<Lance> getLances() {
        return lances;
    }
    
    public void setLances(Collection<Lance> lances) {
        this.lances = lances;
    }
    
    public Collection<Bem> getBens() {
        return bens;
    }
    
    public void setBens(Collection<Bem> bens) {
        this.bens = bens;
    }
}
