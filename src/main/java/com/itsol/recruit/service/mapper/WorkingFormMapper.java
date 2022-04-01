package com.itsol.recruit.service.mapper;

import com.itsol.recruit.dto.UserDTO;
import com.itsol.recruit.dto.WorkingFormDTO;
import com.itsol.recruit.entity.User;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.web.vm.WorkingFormVM;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class WorkingFormMapper implements EntityMapper<WorkingFormDTO,WorkingForm >, RowMapper<WorkingFormDTO> {
    @Override
    public WorkingForm toEntity(WorkingFormDTO dto) {
        if (dto == null) {
            return null;
        }

        WorkingForm entity = new WorkingForm();
        BeanUtils.copyProperties(dto, entity);

        return entity;
    }


    @Override
    public WorkingFormDTO toDto(WorkingForm entity) {
        if (entity == null) {
            return null;
        }

        WorkingFormDTO dto = new WorkingFormDTO();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    @Override
    public List<WorkingForm> toEntity(List<WorkingFormDTO> dtoList) {
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<WorkingFormDTO> toDto(List<WorkingForm> entityList) {
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }


    @Override
    public WorkingFormDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        WorkingFormDTO workingFormDTO=new WorkingFormDTO();
        workingFormDTO.setCode(rs.getString("code"));
        workingFormDTO.setDescription(rs.getString("description"));
        return workingFormDTO;
    }
}
