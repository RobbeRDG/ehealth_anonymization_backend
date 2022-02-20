package be.kul.useraccess.Service.SubService;

import be.kul.useraccess.Entity.ScriptAnonymizationResult;
import be.kul.useraccess.Entity.ScriptSummary;
import be.kul.useraccess.Repository.ScriptAnonymizationResultRepository;
import be.kul.useraccess.Repository.ScriptSummaryRepository;
import be.kul.useraccess.Utils.MongoDB.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataHandlerController {
    @Autowired
    ScriptAnonymizationResultRepository scriptAnonymizationResultRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    ScriptSummaryRepository scriptSummaryRepository;

    public ScriptAnonymizationResult saveScriptExecutionResult(ScriptAnonymizationResult scriptAnonymizationResult) {
        return scriptAnonymizationResultRepository.save(scriptAnonymizationResult);
    }

    public ScriptSummary saveInputScriptSummary(ScriptSummary scriptSummary) {
        //Set the script id before saving
        scriptSummary.setScriptId(sequenceGeneratorService.generateSequence(ScriptSummary.SEQUENCE_NAME));

        return scriptSummaryRepository.save(scriptSummary);
    }

    public ScriptAnonymizationResult getScriptAnonymizationResult(long scriptId) {
        return scriptAnonymizationResultRepository.findByScriptId(scriptId);
    }

}
