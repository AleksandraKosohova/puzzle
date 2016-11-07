package puzzle;

import javax.persistence.*;

@Entity
public class Rules {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name="id_components")
    private Components components;
    @ManyToOne
    @JoinColumn(name="id_diagnosis")
    private Diagnosis diagnosis;

    private String sign;
    private double value;
    private int variant;

    public Rules(Components components, Diagnosis diagnosis, String sign, double value, int variant) {
        this.components = components;
        this.diagnosis = diagnosis;
        this.sign = sign;
        this.value = value;
        this.variant = variant;
    }

    public Rules() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Components getComponents() {
        return components;
    }

    public void setComponents(Components components) {
        this.components = components;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getVariant() {
        return variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public boolean isRuleRight(double value){
        switch(this.sign) {
            case ">":
                if (value > this.value){
                    return true;
                }else {
                    return false;
                }
            case "<":
                if (value < this.value){
                    return true;
                }else {
                    return false;
                }
            case "=":
                if (value == this.value){
                    return true;
                }else {
                    return false;
                }
            case ">=":
                if (value >= this.value){
                    return true;
                }else {
                    return false;
                }
            case "<=":
                if (value <= this.value){
                    return true;
                }else {
                    return false;
                }
            default:
                return false;
        }
    }

}
