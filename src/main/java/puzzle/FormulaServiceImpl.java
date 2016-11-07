package puzzle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class FormulaServiceImpl implements FormulaService{
    @Autowired
    private FormulaRepository formulaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Formula> getFormulas() {
        return formulaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Formula getFormula(Long id) {
        return formulaRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Formula getFormulaByName(String name) {
        return formulaRepository.findByName(name);
    }

    @Override
    @Transactional
    public void insert(Formula formula){
        formulaRepository.saveAndFlush(formula);
    }

    @Override
    @Transactional
    public void delete(Formula formula){
        formulaRepository.delete(formula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Formula> getFormulasNotInList(List<Long> id){
        return formulaRepository.getFormulasNotInList(id);
    }

}
