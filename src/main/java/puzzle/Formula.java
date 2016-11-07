package puzzle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Formula {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String pic;
    private String info;

    @OneToMany(mappedBy="formula", cascade= CascadeType.ALL)
    private List<Composition> compositions = new ArrayList<Composition>();

    @OneToMany(mappedBy="formula", cascade= CascadeType.ALL)
    private List<FormulaDiagnosis> diagnosis = new ArrayList<FormulaDiagnosis>();

    public Formula(String name, String pic, String info) {
        this.name = name;
        this.pic = pic;
        this.info = info;
    }

    public Formula() {
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public List<FormulaDiagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<FormulaDiagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }


}
