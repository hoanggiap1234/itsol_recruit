package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.dto.WorkingFormDTO;
import com.itsol.recruit.entity.WorkingForm;
import com.itsol.recruit.repository.BaseRepository;
import com.itsol.recruit.repository.repoext.UserRepositoryExt;
import com.itsol.recruit.repository.repoext.WorkingFormRepositoryExt;
import com.itsol.recruit.service.mapper.WorkingFormMapper;

import java.util.List;

public class WorkingFormRepositoryImpl extends BaseRepository implements WorkingFormRepositoryExt {
    @Override
    public List<WorkingFormDTO> search() {
        String query ="Select * from working_form ";
        return getNamedParameterJdbcTemplate().query(query, new WorkingFormMapper());
    }
}
