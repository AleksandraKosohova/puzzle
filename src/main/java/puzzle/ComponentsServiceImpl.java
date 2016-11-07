package puzzle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ComponentsServiceImpl implements ComponentsService {
    @Autowired
    private ComponentsRepository componentsRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Components> getComponents(){
        return componentsRepository.findAll();
    }

    @Override
    @Transactional
    public void insert(Components component){
        componentsRepository.saveAndFlush(component);
    }
    @Override
    @Transactional(readOnly = true)
    public Components getComponent(Long id){
        return componentsRepository.findOne(id);
    }
}
