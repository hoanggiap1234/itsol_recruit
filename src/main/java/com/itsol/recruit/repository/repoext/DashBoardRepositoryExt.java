package com.itsol.recruit.repository.repoext;

public interface DashBoardRepositoryExt {
    Object getStatistics(String fromDate, String toDate);
    Object getPieChar(String fromDate, String toDate);
    Object getLineChart(String typeTime, String fromDate, String toDate);
}
