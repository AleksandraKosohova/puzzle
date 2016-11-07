package puzzle;
import javax.persistence.*;

@Entity
public class Composition {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name="id_formula")
    private Formula formula;
    @ManyToOne
    @JoinColumn(name="id_components")
    private Components components;
    private double value;
    private String units;

    public Composition(Formula formula, Components components, double value, String units) {
        this.formula = formula;
        this.components = components;
        this.value = value;
        this.units = units;
    }

    public Composition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {

        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }


}
