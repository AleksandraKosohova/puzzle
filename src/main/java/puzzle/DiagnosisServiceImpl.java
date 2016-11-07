package puzzle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiagnosisServiceImpl implements DiagnosisService{
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Diagnosis> getDiagnosis() {
        return diagnosisRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Diagnosis getDiagnosisById(Long id) {
        return diagnosisRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diagnosis> getFromList(Long[] id) {
        return diagnosisRepository.getDiagnosisFromList(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Diagnosis> getNotFromList(Long[] id) {
        return diagnosisRepository.getDiagnosisNotFromList(id);
    }
}

