package com.wave.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Time_Report_Archive is non-normalised table that holds all the data pushed from CSV file.Along with CSV file name.
 */
@Entity
@Table(name = "Time_Report_Archive")
@NoArgsConstructor
public class TimeReport {

    @Id
    @GenericGenerator(name = "someId", strategy = "increment")
    @GeneratedValue(generator = "someId")
    private Integer ID;

    @Column(name = "file_name", columnDefinition = "varchar")
    @Setter
    @Getter
    private String fileName;
    @Column(name = "date", columnDefinition = "Date")
    @Setter
    @Getter
    private LocalDate date;
    @Column(name = "Hours_worked", columnDefinition = "numeric")
    @Setter
    @Getter
    private Double hoursWorked;
    @Column(name = "employee_id", columnDefinition = "Varchar")
    @Setter
    @Getter
    private String employeeId;
    @Column(name = "job_group", columnDefinition = "Varchar")
    @Setter
    @Getter
    private String jobGroup;

    public TimeReport(String fileName, String fileLine) {
        String[] columns = fileLine.split(",");
        this.fileName = fileName;
        try {
            date = LocalDate.parse(columns[0], DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new ValidationException("Date can not be parsed " + columns[0]);
        }

        try {
            hoursWorked = Double.valueOf(columns[1]);
        } catch (NumberFormatException e) {
            throw new ValidationException("Hours worked not in correct format :" + columns[1]);
        }

        employeeId = columns[2];

        try {
            jobGroup = JobGroup.valueOf(columns[3]).name();
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Job Group Not Valid " + columns[3]);
        }
    }

    @Override
    public String toString() {
        return "TimeReport{" +
                "fileName='" + fileName + '\'' +
                ", date=" + date +
                ", hoursWorked=" + hoursWorked +
                ", employeeId='" + employeeId + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                '}';
    }


}
