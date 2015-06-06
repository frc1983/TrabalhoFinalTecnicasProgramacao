package Model;

public class CategoriaBem {
    
    private Integer id;    
    private String categoria;

    public CategoriaBem() {
    }

    public CategoriaBem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
