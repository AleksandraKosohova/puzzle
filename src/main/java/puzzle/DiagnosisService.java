package puzzle;


import java.util.List;

public interface DiagnosisService {
    public List<Diagnosis> getDiagnosis();
    public Diagnosis getDiagnosisById(Long id);
    public List<Diagnosis> getFromList(Long[] id);
    public List<Diagnosis> getNotFromList(Long[] id);
}
