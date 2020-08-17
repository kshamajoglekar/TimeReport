package com.wave.repository;

import com.wave.entity.TimeReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface TimeReportRepository extends CrudRepository<TimeReport, Integer> {

    @Query("select distinct fileName from TimeReport t where t.fileName=:fileName")
    public Optional<String> findByName(@Param("fileName") String fileName);
}
