package com.wave.util;

import com.wave.entity.TimeReport;
import com.wave.exception.ApplicationException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CommonUtils {

    public static List<String> getFileLines(MultipartFile file,boolean removeHeaders) throws ApplicationException {

        List<String> lines = null;
        try {
            final InputStream inputStream = file.getInputStream();
            /* Assuming that file data will always be alphanumeric*/
            lines = IOUtils.readLines(inputStream, StandardCharsets.US_ASCII);
            if(removeHeaders && lines.size() >0){
                lines.remove(0);
            }
        } catch (IOException e) {
            throw new ApplicationException("Something went wrong while opening file " + file.getName() + " \n " + e.getMessage());
        }

        return lines;
    }

}
