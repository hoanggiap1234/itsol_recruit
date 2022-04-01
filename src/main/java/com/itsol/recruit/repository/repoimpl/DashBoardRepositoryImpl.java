package com.itsol.recruit.repository.repoimpl;

import com.itsol.recruit.dto.LineChart;
import com.itsol.recruit.dto.PieChart;
import com.itsol.recruit.dto.Statistics;
import com.itsol.recruit.repository.BaseRepository;
import com.itsol.recruit.repository.repoext.DashBoardRepositoryExt;
import com.itsol.recruit.utils.SqlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class DashBoardRepositoryImpl extends BaseRepository implements DashBoardRepositoryExt {
    @Override
    public Object getStatistics(String fromDate, String toDate) {
        try {
            String query = SqlReader.getSqlQueryById(SqlReader.ADMIN_DASHBOARD_MODULE, "dashboard_statistics");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_from_date", fromDate);
            parameters.put("p_to_date", toDate);
            return getNamedParameterJdbcTemplate().query(query, parameters, new DashBoardRepositoryImpl.StatisticsMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Object getPieChar(String fromDate, String toDate) {
        try {
            String query = SqlReader.getSqlQueryById(SqlReader.ADMIN_DASHBOARD_MODULE, "dashboard_pie_chart");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_from_date", fromDate);
            parameters.put("p_to_date", toDate);
            return getNamedParameterJdbcTemplate().query(query, parameters, new DashBoardRepositoryImpl.PieChartMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Object getLineChart(String typeTime, String fromDate, String toDate) {
        try {
            String query = SqlReader.getSqlQueryById(SqlReader.ADMIN_DASHBOARD_MODULE, "dashboard_line_chart");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_from_date", fromDate);
            parameters.put("p_to_date", toDate);
            parameters.put("p_date_group_type", typeTime);
            return getNamedParameterJdbcTemplate().query(query, parameters, new DashBoardRepositoryImpl.LineChartMapper());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    class StatisticsMapper implements RowMapper<Statistics> {

        @Override
        public Statistics mapRow(ResultSet rs, int rowNum) throws SQLException {
            Statistics statistics = new Statistics();
            statistics.setInterviewing(rs.getString("interviewing"));
            statistics.setAllJob(rs.getString("all_job"));
            statistics.setNotDone(rs.getString("not_done_job"));
            statistics.setSucessRecruited(rs.getString("sucess_recruited_applicant"));
            statistics.setTotalApply(rs.getString("total_apply"));
            statistics.setTotalView(rs.getString("total_view_job"));
            statistics.setWaitInterview(rs.getString("waiting_for_interview"));
            return statistics;
        }
    }

    class PieChartMapper implements RowMapper<PieChart>{
        @Override
        public PieChart mapRow(ResultSet rs, int rowNum) throws SQLException {
            PieChart pieChart = new PieChart();
            pieChart.setFailApplicantRatio(rs.getString("fail_applicant_ratio"));
            pieChart.setFailApplicantQuantity(rs.getString("fail_applicant_quantity"));
            pieChart.setSucessApplicantQuantity(rs.getString("sucess_applicant_quantity"));
            pieChart.setTotalApplicant(rs.getString("total_applicant"));
            pieChart.setSucessApplicantRatio(rs.getString("sucess_applicant_ratio"));
            return pieChart;
        }
    }

    class LineChartMapper implements RowMapper<LineChart>{

        @Override
        public LineChart mapRow(ResultSet rs, int rowNum) throws SQLException {
            LineChart lineChart = new LineChart();
            lineChart.setLabel(rs.getString("label"));
            lineChart.setQtyPerson(rs.getString("qty_person"));
            lineChart.setSuccessRecruitment(rs.getString("success_recruitment"));
            return lineChart;
        }
    }
}
