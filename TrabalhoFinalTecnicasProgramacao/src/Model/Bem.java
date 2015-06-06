package Model;

public class Bem {

    private Integer id;
    private String descricao;
    private String descricaoCompleta;
    private Lote lote;
    private CategoriaBem categoriaBem;

    public Bem() {
    }

    public Bem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaocompleta() {
        return descricaoCompleta;
    }

    public void setDescricaocompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public CategoriaBem getCategoriaBem() {
        return categoriaBem;
    }

    public void setCategoriaBem(CategoriaBem idcategoriabem) {
        this.categoriaBem = idcategoriabem;
    }
}
