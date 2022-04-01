package com.itsol.recruit.service.impl;

import com.itsol.recruit.repository.repoext.DashBoardRepositoryExt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DashBoardServiceImpl {
    public final DashBoardRepositoryExt dashBoardRepositoryExt;

    public DashBoardServiceImpl(DashBoardRepositoryExt dashBoardRepositoryExt) {
        this.dashBoardRepositoryExt = dashBoardRepositoryExt;
    }

    public Object getStatistics(String fromDate, String toDate){
        return dashBoardRepositoryExt.getStatistics(fromDate, toDate);
    }

    public Object getPieChart(String formDate, String toDate) {
        return dashBoardRepositoryExt.getPieChar(formDate,toDate);
    }

    public Object getLineChart(String typeTime, String formDate, String toDate) {
        return dashBoardRepositoryExt.getLineChart(typeTime,formDate,toDate);
    }

}
