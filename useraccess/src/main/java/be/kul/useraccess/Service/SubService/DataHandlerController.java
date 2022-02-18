package be.kul.useraccess.Service.SubService;

import be.kul.useraccess.Entity.ScriptExecutionResult;
import be.kul.useraccess.Entity.ScriptSummary;
import be.kul.useraccess.Repository.ScriptExecutionResultRepository;
import be.kul.useraccess.Repository.ScriptSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataHandlerController {
    @Autowired
    ScriptExecutionResultRepository scriptExecutionResultRepository;

    @Autowired
    ScriptSummaryRepository scriptSummaryRepository;

    public ScriptExecutionResult saveScriptExecutionResult(ScriptExecutionResult scriptExecutionResult) {
        return scriptExecutionResultRepository.save(scriptExecutionResult);
    }

    public ScriptSummary saveInputScriptSummary(ScriptSummary scriptSummary) {
        return scriptSummaryRepository.save(scriptSummary);
    }

}
