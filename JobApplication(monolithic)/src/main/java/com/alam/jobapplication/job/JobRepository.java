package com.alam.jobapplication.job;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Job j WHERE j.company.id = :companyId")
    void deleteAllByCompanyId(@Param("companyId") long companyId);

}
