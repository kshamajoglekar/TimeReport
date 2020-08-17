package com.wave.validator;

import com.wave.exception.ApplicationException;
import com.wave.exception.ValidationException;
import com.wave.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class CSVValidator {

    final List<String> headerValue = Arrays.asList("date", "hours worked", "employee id", "job group");

    public void validate(final MultipartFile file) throws ApplicationException, ValidationException {

        validateName(file.getOriginalFilename());

        final List<String> lines = CommonUtils.getFileLines(file,false);

        /* at least a header line should be present.*/
        if (Objects.isNull(lines) || lines.size() == 0) throw new ValidationException("Not correct file formatting");

        validateHeader(lines.get(0));
        validateData(lines);
        System.out.println(lines.toString());


    }


    private void validateHeader(final String headerLine) throws ValidationException {
        /* check if headers are correct and in correct order*/
        final String[] headers = StringUtils.split(headerLine, ",");
        for (int i = 0; i < headerValue.size(); i++) {
            if (!StringUtils.equalsIgnoreCase(headers[i], headerValue.get(i)))
                throw new ValidationException("invalid Header line");
        }
    }

    private void validateData(final List<String> lines) throws ValidationException {

        if (lines.size() == 1) return;

        /* Check if each row has correct number of columns and all columns have value */
        boolean isValidData = lines.stream().anyMatch(line -> StringUtils.split(line, ",").length == headerValue.size() &&
                Arrays.stream(StringUtils.split(line, ",")).allMatch(columnValue -> StringUtils.isNotEmpty(columnValue)));

        if (!isValidData) throw new ValidationException("Data Not Valid");
    }

    private void validateName(final String fileName) throws ValidationException {

        boolean invalidFileName = false;

        //1. is non-blank
        if (StringUtils.isEmpty(fileName)){
            throw new ValidationException("File Name is blank");
        }

        //1. has suffix .csv
        if (!fileName.endsWith(".csv")) {
            throw new ValidationException("File name does not end woth .csv");
        }

        //2. Starts with string Time-Report
        if (!StringUtils.startsWithIgnoreCase(fileName, "Time-Report")) {
            throw new ValidationException("File name does not start with Time-Report");
        }

        //3. Has the pattern Time-Report-34 in file name
        final String nameWithoutSuffix = Arrays.stream(StringUtils.split(fileName, ".")).findFirst().get();
        if (StringUtils.split(nameWithoutSuffix, "-").length != 3) {
            throw new ValidationException("File name does not follow naming convention of : Time-Report-Numeric");
        }

        //4. Last part of filename is a number representing ID
        final String lastPart = StringUtils.split(nameWithoutSuffix, "-")[2];
        if (!StringUtils.isNumeric(lastPart)) {
            throw new ValidationException("File name does not have time report id");
        }

    }
}
