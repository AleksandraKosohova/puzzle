package puzzle;

import java.util.List;

public interface FormulaDiagnosisService {
    public List<FormulaDiagnosis> getNoFormulasPrior(List<Long> id, String name);
    public List<FormulaDiagnosis> getDiagnosis(Long id);
    public FormulaDiagnosis getPriorDiagnosis(Long id);
    public List<FormulaDiagnosis> getFormulasPrior(List<Long> idFormulas, String name);
    public void save(FormulaDiagnosis formulaDiagnosis);
    public FormulaDiagnosis getFormulaDiagnosis(Long idFormula, Long idDiagnosis);
    public void deleteFormula(Long id);
    public List<FormulaDiagnosis>  getOnlyPriorFormulas(String name);
    public List<Formula>  getOnlyPriorFormulasChecked(List<Long> idFormulas);
    public List<Formula> getFormulasWithoutPrior(List<Long> idFormula, String name);
    public List<Formula> getFormulasWithoutPriorChecked(List<Long> notIdFomulas, List<Long> idFormulas);
    public FormulaDiagnosis getFormulaDiagnosis(Long id);
    public List<FormulaDiagnosis> getAllFormulas();
    public List<Long> getFormulaId(String name);
    public List<Long> getFormulas(Long[] id, String name);
}
