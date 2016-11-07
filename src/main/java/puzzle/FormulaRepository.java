package puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormulaRepository extends JpaRepository<Formula, Long> {
    @Query("select f from Formula f where f.name = :name")
    public Formula findByName(@Param("name") String name);

    @Query("select f from Formula f where f.id not in (:id)")
    public List<Formula> getFormulasNotInList(@Param("id") List<Long> listId);

}
