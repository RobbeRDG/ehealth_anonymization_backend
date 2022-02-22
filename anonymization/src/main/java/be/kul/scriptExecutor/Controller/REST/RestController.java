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

    @GetMapping("test/update-")
    public ResponseEntity<ScriptAnonymizationResult> uploadAnonymizationSummary(
            @RequestBody ScriptSummary scriptSummary
    ) {
        ScriptAnonymizationResult scriptAnonymizationResult = scriptExecutorService.handleAnonymizationRequest(scriptSummary);

        return new ResponseEntity<>(
                scriptAnonymizationResult,
                HttpStatus.OK
        );
    }


}