package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects;

import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.DataHandle;

public class DataSetAnonymizationResult {
    private ARXResult anonymizedData;
    private DataHandle handle;

    public DataSetAnonymizationResult(ARXResult anonymizedData, DataHandle handle) {
        this.anonymizedData = anonymizedData;
        this.handle = handle;
    }

    public ARXResult getAnonymizedData() {
        return anonymizedData;
    }

    public DataHandle getHandle() {
        return handle;
    }
}

