package be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects;

import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.criteria.EqualDistanceTCloseness;
import org.deidentifier.arx.criteria.HierarchicalDistanceTCloseness;
import org.deidentifier.arx.criteria.KAnonymity;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ArxAnonymizationConfigurer {
    public static void configure(Data data, ARXConfiguration configuration) {
        //Set a global anonymization model for the quasi identifiers
        generateGlobalConfiguration(configuration);

        //Get the sensitive attributes
        Set<String> sensitiveAttributes = data.getDefinition().getSensitiveAttributes();

        //Add the configuration for the sensitive attributes
        for (String sensitiveAttribute : sensitiveAttributes) {
            switch (sensitiveAttribute) {
                case "death_datetime":
                    generateDeathDateConfiguration(sensitiveAttribute, data, configuration);
                    break;
                case "cause_concept_id":
                    generateCauseConceptIdConfiguration(sensitiveAttribute, configuration);
                    break;
                default:
                    throw new DataSetAnonymizationException(
                            "No anonymization model configuration for sensitive attribute: " + sensitiveAttribute
                    );
            }
        }
    }

    private static void generateGlobalConfiguration(ARXConfiguration configuration) {
        configuration.addPrivacyModel(new KAnonymity(2));
        configuration.setSuppressionLimit(1.0);
    }

    private static void generateCauseConceptIdConfiguration(String attributeName, ARXConfiguration configuration) {
        configuration.addPrivacyModel(
                new EqualDistanceTCloseness(
                        attributeName,
                        0.5
                )
        );
    }

    private static void generateDeathDateConfiguration(String attributeName, Data data, ARXConfiguration configuration) {
        configuration.addPrivacyModel(
                new HierarchicalDistanceTCloseness(
                        attributeName,
                        0.5,
                        data.getDefinition().getHierarchyObject(attributeName)
                )
        );
    }
}

