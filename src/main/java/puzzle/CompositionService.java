package puzzle;


import java.util.List;

public interface CompositionService {
    public List<Composition> getCompositions();
    public List<Composition> getCompositionsForFormula(Long id);
    public void insert(Composition comp);
    public Composition findComponent(Long id, Long idComponent);
}
