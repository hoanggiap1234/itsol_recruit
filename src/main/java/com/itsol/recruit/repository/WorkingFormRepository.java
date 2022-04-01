package com.itsol.recruit.repository;

import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.repository.repoext.WorkingFormRepositoryExt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingFormRepository extends JpaRepository<WorkingForm, Long>, WorkingFormRepositoryExt {
    WorkingForm findWorkingFormByCode(String code);
    WorkingForm findWorkingFormById(Long id);

}
