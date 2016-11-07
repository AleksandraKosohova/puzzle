package puzzle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RulesServiceImpl implements RulesService{
    @Autowired
    private RulesRepository rulesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rules> findByVar(int var) {
        return rulesRepository.findByVar(var);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> getVariants(){return rulesRepository.getVariants();};
}
