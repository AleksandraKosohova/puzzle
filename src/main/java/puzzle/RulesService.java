package puzzle;

import java.util.List;

public interface RulesService {
    public List<Rules> findByVar(int var);
    public List<Integer> getVariants();
}
