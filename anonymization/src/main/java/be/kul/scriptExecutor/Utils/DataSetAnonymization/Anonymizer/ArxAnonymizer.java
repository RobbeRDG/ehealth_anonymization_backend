package be.kul.scriptExecutor.Utils.DataSetAnonymization.Anonymizer;

import be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects.ArxAnonymizationConfigurer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.certificate.elements.ElementData;
import org.deidentifier.arx.criteria.*;
import org.deidentifier.arx.metric.Metric;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ArxAnonymizer {
    public DataHandle anonymize(DataSubset researchDataSubset, Data researchData) throws IOException {
        //Create a configuration object
        ARXConfiguration configuration = ARXConfiguration.create();

        //Pass the configuration to the anonymization configurer
        ArxAnonymizationConfigurer.configure(researchDataSubset, configuration);

        //Set the information loss metric
        configuration.setQualityModel(Metric.createLossMetric());

        //Set the anonymizer
        ARXAnonymizer anonymizer = new ARXAnonymizer();

        //Execute the anonymization
        ARXResult result = anonymizer.anonymize(researchData,configuration);

        //Get a handle on the data
        DataHandle handle = result.getOutput();

        //throw error if arx couldn't find an appropriate anonymization
        if (handle == null) throw new DataSetAnonymizationException(
                "No privacy preserving transformation could be executed"
        );

        return handle;
    }


    public DataHandle anonymizeWithTestDelta(DataSubset researchSubset, DefaultData researchData, double delta) throws IOException {
        //Create a configuration object
        ARXConfiguration configuration = ARXConfiguration.create();

        //Pass the configuration to the anonymization configurer
        ArxAnonymizationConfigurer.configureWithTestDelta(researchSubset, configuration, 0, delta);

        //Set the information loss metric
        configuration.setQualityModel(Metric.createLossMetric());

        //Set the anonymizer
        ARXAnonymizer anonymizer = new ARXAnonymizer();

        //Release the research data handle if needed
        if (!researchData.getHandle().isReleased()) researchData.getHandle().release();

        //Execute the anonymization
        ARXResult result = anonymizer.anonymize(researchData,configuration);

        //Get a handle on the data
        DataHandle dataHandle = result.getOutput();

        //throw error if arx couldn't find an appropriate anonymization
        if (dataHandle == null) throw new DataSetAnonymizationException(
                "No privacy preserving transformation could be executed"
        );

        return dataHandle;
    }
}
