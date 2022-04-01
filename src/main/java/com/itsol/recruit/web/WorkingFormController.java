package com.itsol.recruit.web;

import com.itsol.recruit.core.Constants;
import com.itsol.recruit.dto.WorkingFormDTO;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.service.impl.WorkingFormServiceImpl;
import com.itsol.recruit.web.vm.WorkingFormSearchVM;
import com.itsol.recruit.web.vm.WorkingFormVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Constants.Api.Path.PUBLIC)
public class WorkingFormController {
    public final WorkingFormServiceImpl workingFormService;

    public WorkingFormController(WorkingFormServiceImpl workingFormService) {
        this.workingFormService = workingFormService;
    }

    @GetMapping(value = "/workingform")
    public ResponseEntity<List<WorkingFormDTO>> search(){
        return ResponseEntity.ok().body(workingFormService.search());
    }

    @PutMapping(value = "/workingform")
    public ResponseEntity<?> createrWorkingForm(@Valid @RequestBody WorkingFormVM workingFormVM){
        if(workingFormService.findByCode(workingFormVM.getCode())==null){
            workingFormService.save(workingFormVM);
            return ResponseEntity.ok("Working form created");
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "/workingform")
    public ResponseEntity<?> updateWorkingForm(@Valid @RequestBody WorkingFormVM workingFormVM){
        return workingFormService.update(workingFormVM);
    }

    @PostMapping(value = "/workingform/delete/{id}")
    public ResponseEntity<?> deleteWorkingForm(@RequestParam Long id){
        return workingFormService.delete(id);
    }

    @PostMapping(value = "/workingform/search")
    public ResponseEntity<?> search1(@Valid @RequestBody WorkingFormSearchVM workingFormSearchVM,@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<WorkingFormDTO> workingForms = workingFormService.search_1(workingFormSearchVM, pageable);
        return  new ResponseEntity<>(workingForms, HttpStatus.OK);
    }
}
