package puzzle;


import java.util.List;

public interface ComponentsService {
    public List<Components> getComponents();
    public void insert(Components component);
    public Components getComponent(Long id);
}
