package Model;

import java.io.Serializable;
import java.util.Collection;

public class Usuario implements Serializable{
    
    private Integer id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private Collection<Leilao> leiloes;
    private Collection<Lance> lances;
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, String cpfCnpj, String email, TipoUsuario tipo) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.tipoUsuario = tipo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfcnpj) {
        this.cpfCnpj = cpfcnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Leilao> getLeiloes() {
        return leiloes;
    }

    public void setLeiloes(Collection<Leilao> leiloes) {
        this.leiloes = leiloes;
    }

    public Collection<Lance> getLances() {
        return lances;
    }

    public void setLances(Collection<Lance> lances) {
        this.lances = lances;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario idtipousuario) {
        this.tipoUsuario = idtipousuario;
    }
}
