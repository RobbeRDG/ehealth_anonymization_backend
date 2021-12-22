package be.kul.scriptExecutor.Controller.REST;

import be.kul.scriptExecutor.Service.ScriptExecutorService;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.TreeStructure.ScriptSummary.ScriptSummary;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private ScriptExecutorService scriptExecutorService;

    @PostMapping("test/anonymize")
    public ResponseEntity<HashMap<String, DataContainer>> uploadAnonymizationSummary(
            @RequestBody ScriptSummary scriptSummary
    ) {
        return scriptExecutorService.executeSummary(scriptSummary);
    }

}