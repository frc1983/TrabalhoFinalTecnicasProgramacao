package Domain;

public class FormaLance {
    
    private Integer id;
    private String forma;

    public FormaLance() {
    }

    public FormaLance(Integer id, String forma) {
        this.id = id;
        this.forma = forma;
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
