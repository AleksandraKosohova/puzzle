package puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, Long> {
    @Query("SELECT c FROM Composition c where c.formula.id = :id")
    List<Composition> findByFormula(@Param("id") Long id);

    @Query("SELECT c FROM Composition c where c.formula.id = :id and c.components.id = :idComponent")
    Composition findComponent(@Param("id") Long id, @Param("idComponent") Long idComponent);
}
