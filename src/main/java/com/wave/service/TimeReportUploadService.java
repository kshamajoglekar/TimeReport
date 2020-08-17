package com.wave.service;

import com.wave.entity.TimeReport;
import com.wave.exception.ApplicationException;
import com.wave.exception.ValidationException;
import com.wave.repository.TimeReportRepository;
import com.wave.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TimeReportUploadService {

    @Autowired
    private TimeReportRepository timeReportRepository;

    /**
     * @param file What This API Does:
     *             Uploads the Time-report to database.
     *             Checks if the time-report file was already uploaded
     *             What This API Does NOT:
     *             Check if the Time-report data was uploaded for the same date before.
     * @throws ApplicationException
     * @throws ValidationException
     */
    public void uploadTimeReport(MultipartFile file) throws ApplicationException, ValidationException {

        final String fileName = file.getOriginalFilename();

        throwIfAlreadyUploaded(fileName);

        final List<String> lines = CommonUtils.getFileLines(file, true);
        final List<TimeReport> timeReports = lines.stream().map(line -> new TimeReport(fileName, line)).collect(Collectors.toList());

        timeReportRepository.saveAll(timeReports);
    }

    private void throwIfAlreadyUploaded(String fileName) throws ValidationException {
        Optional<String> existFile = timeReportRepository.findByName(fileName);
        if (existFile.isPresent()) {
            throw new ValidationException("The time report already uploaded :" + fileName);
        }
    }

}
