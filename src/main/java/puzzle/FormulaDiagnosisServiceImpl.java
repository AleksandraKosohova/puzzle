package puzzle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormulaDiagnosisServiceImpl implements FormulaDiagnosisService {
    @Autowired
    private FormulaDiagnosisRepository formulaDiagnosisRepository;



    @Override
    @Transactional(readOnly = true)
    public List<FormulaDiagnosis> getDiagnosis(Long idFormula) {
        return formulaDiagnosisRepository.getDiagnosis(idFormula);
    }

    @Override
    @Transactional(readOnly = true)
    public FormulaDiagnosis getPriorDiagnosis(Long idFormula) {
        return formulaDiagnosisRepository.getPriorDiagnosis(idFormula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormulaDiagnosis> getFormulasPrior(List<Long> idFormulas, String name) {
        return formulaDiagnosisRepository.getFormulasPrior(idFormulas, "%" + name + "%");
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormulaDiagnosis> getNoFormulasPrior(List<Long> idFormulas, String name) {
        return formulaDiagnosisRepository.getNoPriorFormulas(idFormulas, "%" + name + "%");
    }

    @Override
    @Transactional
    public void save(FormulaDiagnosis formulaDiagnosis){
        formulaDiagnosisRepository.saveAndFlush(formulaDiagnosis);
    }

    @Override
    @Transactional
    public void deleteFormula(Long id){
        formulaDiagnosisRepository.deleteFormula(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormulaDiagnosis>  getOnlyPriorFormulas(String name) {
        return formulaDiagnosisRepository.getOnlyPriorFormulas("%" + name + "%");
    }
    @Override
    @Transactional(readOnly = true)
    public List<Formula>  getOnlyPriorFormulasChecked(List<Long> idFormulas){
        return formulaDiagnosisRepository.getOnlyPriorFormulasChecked(idFormulas);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Formula> getFormulasWithoutPrior(List<Long> idFormula, String name) {
        return formulaDiagnosisRepository.getFormulasWithoutPrior(idFormula, "%" + name + "%");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Formula> getFormulasWithoutPriorChecked(List<Long> notIdFomulas, List<Long> idFormulas){
        return formulaDiagnosisRepository.getFormulasWithoutPriorChecked(notIdFomulas, idFormulas);
    }

    @Override
    @Transactional(readOnly = true)
    public FormulaDiagnosis getFormulaDiagnosis(Long id){
        return formulaDiagnosisRepository.getFormulaDiagnosis(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FormulaDiagnosis>  getAllFormulas() {
        return formulaDiagnosisRepository.getAllFormulas();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getFormulaId(String name){
        return formulaDiagnosisRepository.getFormulaId("%" + name + "%");
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getFormulas(Long[] idDiagnosis, String name) {
        return formulaDiagnosisRepository.getFormulas(idDiagnosis, Long.valueOf(idDiagnosis.length), "%" + name + "%");
    }

    @Override
    @Transactional(readOnly = true)
    public FormulaDiagnosis getFormulaDiagnosis(Long idFormula, Long idDiagnosis){
        return formulaDiagnosisRepository.getFormulaDiagnosis(idFormula, idDiagnosis);
    }
}
