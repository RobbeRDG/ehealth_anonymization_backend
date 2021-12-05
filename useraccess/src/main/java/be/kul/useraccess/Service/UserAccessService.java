package be.kul.useraccess.Service;

import be.kul.useraccess.Entity.ScriptSummary;
import be.kul.useraccess.Utils.Components.ScriptParser.ScriptParserController;
import be.kul.useraccess.Utils.Exceptions.ExceptionClasses.ScriptFileToStringConversionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class UserAccessService {
    @Autowired
    ScriptParserController scriptParserController;

    public ResponseEntity<String> uploadScript(MultipartFile scriptFile) throws ScriptFileToStringConversionException, JsonProcessingException {
        String scriptString = "";
        try {
            scriptString = new String(scriptFile.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.warn("Something went wrong converting script file to string: " + e.getLocalizedMessage());
            throw new ScriptFileToStringConversionException("Couldn't convert specified script file to string: " + e.getLocalizedMessage());
        }

        //Send the script string to the parser
        ScriptSummary scriptSummary = scriptParserController.generateAnonymizationSummary(scriptString);


        String anonymizationSummaryString = new ObjectMapper().writeValueAsString(scriptSummary);


        return new ResponseEntity<String>(anonymizationSummaryString, HttpStatus.OK);

    }
}
