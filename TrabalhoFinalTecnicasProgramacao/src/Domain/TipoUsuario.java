package Domain;

public class TipoUsuario {

    private Integer id;
    private String text;

    public TipoUsuario() {
    }

    public TipoUsuario(Integer id, String tipo) {
        this.id = id;
        this.text = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return text;
    }

    public void setTipo(String tipo) {
        this.text = tipo;
    }
}
