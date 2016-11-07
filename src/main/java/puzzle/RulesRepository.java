package puzzle;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RulesRepository extends JpaRepository<Rules, Long> {
    @Query("select r from Rules r where r.variant = :var")
    public List<Rules> findByVar(@Param("var") int var);

    @Query("select r.variant from Rules r group by r.variant")
    public List<Integer> getVariants();

}
