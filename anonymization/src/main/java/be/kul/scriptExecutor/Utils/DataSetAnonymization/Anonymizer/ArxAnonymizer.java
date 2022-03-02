package be.kul.scriptExecutor.Utils.DataSetAnonymization.Anonymizer;

import be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects.ArxAnonymizationConfigurer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.Data.DefaultData;
import org.deidentifier.arx.criteria.DistinctLDiversity;
import org.deidentifier.arx.criteria.EqualDistanceTCloseness;
import org.deidentifier.arx.criteria.KAnonymity;
import org.deidentifier.arx.criteria.OrderedDistanceTCloseness;
import org.deidentifier.arx.metric.Metric;

import java.io.IOException;
import java.util.Set;

public class ArxAnonymizer {
    public static DataHandle anonymize(DefaultData data, ARXPopulationModel population) throws IOException {
        //Create a configuration object
        ARXConfiguration configuration = ARXConfiguration.create();

        //Pass the configuration to the anonymization configurer
        ArxAnonymizationConfigurer.configure(data, configuration);

        //Set the information loss metric
        configuration.setQualityModel(Metric.createEntropyMetric());

        //Set the anonymizer
        ARXAnonymizer anonymizer = new ARXAnonymizer();

        //Execute the anonymization
        ARXResult result = anonymizer.anonymize(data,configuration);

        //Get a handle on the data
        DataHandle handle = result.getOutput();

        //throw error if arx couldn't find an appropriate anonymization
        if (handle == null) throw new DataSetAnonymizationException(
                "No privacy preserving transformation could be executed"
        );

        return handle;
    }
}
