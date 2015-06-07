package Domain;

public class FormaLance {
    
    private Integer id;
    private String forma;

    public FormaLance() {
    }

    public FormaLance(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
