package be.kul.scriptExecutor.Utils.DataSetAnonymization.Anonymizer;

import org.deidentifier.arx.*;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.criteria.DistinctLDiversity;
import org.deidentifier.arx.criteria.KAnonymity;
import org.deidentifier.arx.metric.Metric;

import java.io.IOException;

public class ArxAnonymizer {
    public static DataHandle anonymize(DefaultData data, ARXPopulationModel population) throws IOException {
        //Create a configuration object
        ARXConfiguration configuration = ARXConfiguration.create();

        /*
        //Set the anonymization configuration
        configuration.addPrivacyModel(new KAnonymity(11));
        if (!data.getDefinition().getSensitiveAttributes().isEmpty()) {
            configuration.addPrivacyModel(new DistinctLDiversity("cause_concept_id", 2));
        }
         */

        //Set the information loss metric
        configuration.setQualityModel(Metric.createEntropyMetric());

        //Set the aonymizer
        ARXAnonymizer anonymizer = new ARXAnonymizer();

        //Execute the anonymization
        ARXResult result = anonymizer.anonymize(data,configuration);

        //Get a handle on the data
        DataHandle handle = result.getOutput();

        return handle;
    }
}
