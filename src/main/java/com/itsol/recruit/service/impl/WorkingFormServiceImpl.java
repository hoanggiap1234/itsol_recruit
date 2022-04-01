package com.itsol.recruit.service.impl;

import com.itsol.recruit.dto.WorkingFormDTO;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.repository.WorkingFormRepository;
import com.itsol.recruit.service.mapper.WorkingFormMapper;
import com.itsol.recruit.web.vm.WorkingFormSearchVM;
import com.itsol.recruit.web.vm.WorkingFormVM;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class WorkingFormServiceImpl {
    public final WorkingFormRepository workingFormRepository;
    public final WorkingFormMapper workingFormMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public WorkingFormServiceImpl(WorkingFormRepository workingFormRepository, WorkingFormMapper workingFormMapper) {
        this.workingFormRepository = workingFormRepository;
        this.workingFormMapper = workingFormMapper;
    }

    public List<WorkingFormDTO> search(){
        return workingFormRepository.search();
    }

    public WorkingForm findByCode(String code){return workingFormRepository.findWorkingFormByCode(code);}


    public void save(WorkingFormVM workingFormVM){
        WorkingForm workingForm=new WorkingForm();
        BeanUtils.copyProperties(workingFormVM, workingForm);
        workingForm.setDelete(false);
        workingFormRepository.save(workingForm);
    }

    public ResponseEntity<?> update(WorkingFormVM workingFormVM){
        WorkingForm workingForm=new WorkingForm();
        workingForm=workingFormRepository.findWorkingFormByCode(workingForm.getCode());
        if(workingForm==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            BeanUtils.copyProperties(workingFormVM, workingForm);
            workingFormRepository.save(workingForm);
            return ResponseEntity.ok("Working form updated");
        }
    }

    public ResponseEntity<?> delete(Long id){
        WorkingForm workingForm=new WorkingForm();
        workingForm=workingFormRepository.findWorkingFormById(id);
        if(workingForm==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            workingForm.setDelete(true);
            workingFormRepository.save(workingForm);
            return ResponseEntity.ok("Working form deleted");
        }
    }

    public Page<WorkingFormDTO> search_1(WorkingFormSearchVM workingFormSearchVM, Pageable pageable){
        List<WorkingFormDTO> workingForms=new ArrayList<>();
        Page<WorkingFormDTO> workingFormPageable =new PageImpl<>(workingForms, pageable, 0);
        Map<String, Object> parameters =new HashMap<>();
        try {
            StringBuilder sb=new StringBuilder();
            sb.append("Select * from working_form");
            sb.append(" where 1=1");
            if(!ObjectUtils.isEmpty(workingFormSearchVM.getCode())){
                sb.append(" and code like :Code");
                parameters.put("Code","%"+ workingFormSearchVM.getCode() +"%");
            }
            Query query= entityManager.createNativeQuery(sb.toString(), WorkingForm.class );
            int total=0;
            total=query.getResultList().size();
            if(pageable.getPageSize() !=0 && pageable.getPageNumber()!=0){

                query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize() - pageable.getPageSize());
                query.setMaxResults(pageable.getPageNumber() * pageable.getPageSize());
            }
            else {
                query.setFirstResult(1);
                query.setMaxResults(10);
            }
            List<WorkingForm> workingFormList =query.getResultList();
            List<WorkingFormDTO> workingFormDTOS =workingFormMapper.toDto(workingFormList);
            workingFormPageable=new PageImpl<>(workingFormDTOS, pageable, total);

        }catch (Exception e){

        }
        return workingFormPageable;
    }
}
