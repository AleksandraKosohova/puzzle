package puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface FormulaDiagnosisRepository extends JpaRepository<FormulaDiagnosis, Long>{

    @Query("SELECT f FROM FormulaDiagnosis f where f.formula.id = :idFormula")
    List<FormulaDiagnosis> getDiagnosis(@Param("idFormula") Long idFormula);

    @Query("SELECT f FROM FormulaDiagnosis f where f.formula.id in (:idFormulas) and f.prior = 1 and f.formula.name like :name")
    List<FormulaDiagnosis> getFormulasPrior(@Param("idFormulas") List<Long> formulas, @Param("name") String name);

    @Query("DELETE FROM FormulaDiagnosis f where f.formula.id = :idFormula")
    void deleteFormula(@Param("idFormula") Long idFormula);

    @Query("SELECT f FROM FormulaDiagnosis f where f.formula.id in (:idFormulas) and f.formula.name like :name")
    List<FormulaDiagnosis> getNoPriorFormulas(@Param("idFormulas") List<Long> formulas, @Param("name") String name);

    @Query("SELECT f  FROM FormulaDiagnosis f where f.prior = 1 and f.formula.name like :name order by f.diagnosis.id asc")
    List<FormulaDiagnosis>  getOnlyPriorFormulas(@Param("name") String name);

    @Query("SELECT f.formula FROM FormulaDiagnosis f where f.prior = 1 and f.formula.id in(:idFormulas) order by f.diagnosis.id asc")
    List<Formula>  getOnlyPriorFormulasChecked(@Param("idFormulas") List<Long> idFormulas);

    @Query("SELECT distinct(f.formula) FROM FormulaDiagnosis f where f.formula.id not in (:idFormulas) and f.formula.name like :name ")
    List<Formula> getFormulasWithoutPrior(@Param("idFormulas") List<Long> formulas, @Param("name") String name);

    @Query("SELECT distinct(f.formula) FROM FormulaDiagnosis f where f.formula.id not in (:notIdFormulas) and f.formula.id in(:idFormulas)")
    List<Formula> getFormulasWithoutPriorChecked(@Param("notIdFormulas") List<Long> notIdFormulas, @Param("idFormulas") List<Long> idFormulas);

    @Query("SELECT max(f) FROM FormulaDiagnosis f where f.formula.id = :id")
    FormulaDiagnosis getFormulaDiagnosis(@Param("id") Long id);

    @Query("SELECT distinct f FROM FormulaDiagnosis f where f.formula.id = :idFormula and f.prior = 1")
    FormulaDiagnosis getPriorDiagnosis(@Param("idFormula") Long idFormula);

    @Query("SELECT f from FormulaDiagnosis f order by f.prior desc, f.diagnosis.id asc")
    List<FormulaDiagnosis> getAllFormulas();

    @Query("SELECT distinct(f.formula.id) FROM FormulaDiagnosis f where f.formula.name like :name ")
    List<Long> getFormulaId(@Param("name") String name);

    @Query("SELECT f.formula.id FROM FormulaDiagnosis f where f.diagnosis.id in (:idDiag) and f.formula.name like :name group by f.formula.id " +
            " having count(distinct f.diagnosis.id) = :idCount ")

    List<Long> getFormulas(@Param("idDiag") Long[] idDiagnosis, @Param("idCount") Long count, @Param("name") String name);

    @Query("SELECT f FROM FormulaDiagnosis f where f.formula.id = :idFormula and f.diagnosis.id = :idDiagnosis")
    FormulaDiagnosis getFormulaDiagnosis(@Param("idFormula") Long idFormula, @Param("idDiagnosis") Long idDiagnosis);
}

