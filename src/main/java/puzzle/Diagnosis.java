package puzzle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private byte isCheck;

    @OneToMany(mappedBy="diagnosis", cascade= CascadeType.ALL)
    private List<FormulaDiagnosis> diagnosis = new ArrayList<FormulaDiagnosis>();

    @OneToMany(mappedBy="diagnosis", cascade= CascadeType.ALL)
    private List<Rules> rules = new ArrayList<Rules>();

    public Diagnosis(String name) {
        this.name = name;
    }

    public Diagnosis() {
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

    public byte getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(byte isCheck) {
        this.isCheck = isCheck;
    }

    public List<FormulaDiagnosis> getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(List<FormulaDiagnosis> diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Rules> getRules() {
        return rules;
    }

    public void setRules(List<Rules> rules) {
        this.rules = rules;
    }


}
