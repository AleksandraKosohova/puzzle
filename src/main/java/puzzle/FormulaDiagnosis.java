package puzzle;

import javax.persistence.*;

@Entity
public class FormulaDiagnosis {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name="id_formula")
    private Formula formula;

    @ManyToOne
    @JoinColumn(name="id_diagnosis")
    private Diagnosis diagnosis;

    private int prior;

    public FormulaDiagnosis(Formula formula, Diagnosis diagnosis, int prior) {
        this.formula = formula;
        this.diagnosis = diagnosis;
        this.prior = prior;
    }

    public FormulaDiagnosis() {
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

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getPrior() {
        return prior;
    }

    public void setPrior(int prior) {
        this.prior = prior;
    }


}
