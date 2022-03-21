package be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects;

import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.criteria.DPresence;
import org.deidentifier.arx.criteria.EqualDistanceTCloseness;
import org.deidentifier.arx.criteria.HierarchicalDistanceTCloseness;
import org.deidentifier.arx.criteria.KAnonymity;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArxAnonymizationConfigurer {
    public static void configure(DataSubset researchData, ARXConfiguration configuration) {
        //Set a global anonymization model for the quasi identifiers
        generateGlobalConfiguration(configuration, researchData);
    }

    private static void generateGlobalConfiguration(ARXConfiguration configuration, DataSubset researchData) {
        configuration.addPrivacyModel(new DPresence(0, 0.4, researchData));
        configuration.setSuppressionLimit(0);
    }

    public static void configureWithTestDelta(DataSubset researchSubset, ARXConfiguration configuration, double minTestDelta, double maxTestDelta) {
        configuration.addPrivacyModel(new DPresence(minTestDelta, maxTestDelta, researchSubset));
        configuration.setSuppressionLimit(1.0);
    }
}

