package puzzle;


import java.util.List;

public interface FormulaService {
    public Formula getFormula(Long id);
    public Formula getFormulaByName(String name);
    public List<Formula> getFormulas();
    public void insert(Formula formula);
    public void delete(Formula formula);
    public List<Formula> getFormulasNotInList(List<Long> id);
}
