package puzzle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CompositionServiceImpl implements CompositionService {

    @Autowired
    private CompositionRepository compositionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Composition> getCompositions() {
        return compositionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Composition> getCompositionsForFormula(Long id) {
        return compositionRepository.findByFormula(id);
    }

    @Override
    @Transactional
    public void insert(Composition comp){
        compositionRepository.saveAndFlush(comp);
    }

    @Override
    @Transactional(readOnly = true)
    public Composition findComponent(Long id, Long idComponent){
        return compositionRepository.findComponent(id, idComponent);
    }
}
