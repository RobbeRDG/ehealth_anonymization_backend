package be.kul.scriptExecutor.Controller.REST;

import be.kul.scriptExecutor.Utils.ScriptAnonymizationResult.ScriptAnonymizationResult;
import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ScriptSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private ScriptExecutorService scriptExecutorService;

    @PostMapping("test/anonymize")
    public ResponseEntity<ScriptAnonymizationResult> uploadAnonymizationSummary(
            @RequestBody ScriptSummary scriptSummary
    ) {
        ScriptAnonymizationResult scriptAnonymizationResult = scriptExecutorService.handleAnonymizationRequest(scriptSummary);

        return new ResponseEntity<>(
                scriptAnonymizationResult,
                HttpStatus.OK
        );
    }

    @GetMapping("test/anonymize/sql")
    public ResponseEntity<String> uploadAnonymizationSummary(
            @RequestParam("sql") String sqlQuery,
            @RequestParam("deltaStart") String deltaStartString,
            @RequestParam("deltaStop") String deltaStopString,
            @RequestParam("deltaStep") String deltaStepString
    ) {
        double deltaStart = Double.parseDouble(deltaStartString);
        double deltaStop = Double.parseDouble(deltaStopString);
        double deltaStep = Double.parseDouble(deltaStepString);

        String result = scriptExecutorService.testDPresenceAnonymization(sqlQuery,deltaStart,deltaStop,deltaStep);

        return new ResponseEntity<>(
                result,
                HttpStatus.OK
        );
    }




}