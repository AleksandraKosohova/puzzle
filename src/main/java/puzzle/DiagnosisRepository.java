package puzzle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    @Query("SELECT d FROM Diagnosis d where d.id in (:idDiag)")
    List<Diagnosis> getDiagnosisFromList(@Param("idDiag") Long[] idDiagnosis);

    @Query("SELECT d FROM Diagnosis d where d.id not in (:idDiag)")
    List<Diagnosis> getDiagnosisNotFromList(@Param("idDiag") Long[] idDiagnosis);
}
