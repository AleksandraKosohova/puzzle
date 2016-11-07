package puzzle;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Components {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String defaultUnits;

    @OneToMany(mappedBy="components", cascade= CascadeType.ALL)
    private List<Composition> compositions = new ArrayList<Composition>();
    @OneToMany(mappedBy="components", cascade= CascadeType.ALL)
    private List<Rules> rules = new ArrayList<Rules>();

    public Components(String name) {
        this.name = name;
    }

    public Components() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public List<Rules> getRules() {
        return rules;
    }

    public void setRules(List<Rules> rules) {
        this.rules = rules;
    }

    public String getDefaultUnits() {
        return defaultUnits;
    }

    public void setDefaultUnits(String defaultUnits) {
        this.defaultUnits = defaultUnits;
    }
}
