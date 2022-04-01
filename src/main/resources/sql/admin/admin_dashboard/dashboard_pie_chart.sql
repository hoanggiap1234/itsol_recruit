with time_filtered_job as (
    select*
    from job
    where start_recruitment_date between to_date(:p_from_date, 'YYYYMMDD') and to_date(:p_to_date, 'YYYYMMDD')
), sucess_applicant as (
    select count(jr.id) sucess_applicant
    from time_filtered_job j
             INNER join job_register jr on jr.job_id = j.id
             INNER join status_job_register ps on ps.id = jr.status_id and ps.code = 'SUCCESS'
), fail_applicant as (
    select count(jr.id) fail_applicant
    from time_filtered_job j
             INNER join job_register jr on jr.job_id = j.id
             INNER join status_job_register ps on ps.id = jr.status_id and ps.code in ('REJECT','REJECT_INTERVIEW', 'LOST')
), total_applicant as (
    select count(jr.id) total_applicant
    from time_filtered_job j
             INNER join job_register jr on jr.job_id = j.id
             INNER join status_job_register ps on ps.id = jr.status_id and ps.code in (select status_job_register.code from status_job_register)
)
select total_applicant,
       sucess_applicant sucess_applicant_quantity,
       case when total_applicant != 0 then sucess_applicant/total_applicant else null end sucess_applicant_ratio,
       fail_applicant fail_applicant_quantity,
       case when total_applicant != 0 then fail_applicant/total_applicant else null end fail_applicant_ratio
from total_applicant, sucess_applicant, fail_applicant
