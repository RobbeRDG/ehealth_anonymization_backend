package be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects;

import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.aggregates.HierarchyBuilderDate;

import java.util.HashMap;
import java.util.List;

public class ArxColumnInformationConfigurer {
    public static void generateMap(List<String> attributeNames, Data data) {
        //Add the information for each attribute to the map
       for (String attributeName : attributeNames) {
           switch (attributeName) {
               case "person_id":
                   generatePersonIdInformation(attributeName, data);
                   break;
               case "gender_concept_id":
                   generateGenderConceptIdInformation(attributeName, data);
                   break;
               case "birth_datetime":
                   generateBirthDateTimeInformation(attributeName, data);
                   break;
               case "race_concept_id":
                   generateRaceConceptIdInformation(attributeName, data);
                   break;
               case "death_datetime":
                   generateDeathDateTimeInformation(attributeName, data);
                   break;
               case "cause_concept_id":
                   generateCauseConceptIdInformation(attributeName, data);
                   break;
               default:
                   throw new DataSetAnonymizationException("No anonymization configuration for attribute: " + attributeName);
           }
       }
    }

    private static void generateCauseConceptIdInformation(String attributeName, Data data) {
        //Set the datatype
        data.getDefinition().setDataType(
                attributeName,
                DataType.INTEGER
        );

        //Set the attribute type
        data.getDefinition().setAttributeType(
                attributeName,
                AttributeType.SENSITIVE_ATTRIBUTE
        );
    }

    private static void generateDeathDateTimeInformation(String attributeName, Data data) {
        //Set the datatype
        data.getDefinition().setDataType(
                attributeName,
                DataType.createDate("yyyy-MM-dd hh:mm:ss")
        );

        //Set the attribute type
        data.getDefinition().setAttributeType(
                attributeName,
                AttributeType.SENSITIVE_ATTRIBUTE
        );

        //Set the hierarchy
        data.getDefinition().setHierarchy(
                attributeName,
                HierarchyBuilderDate.create(
                        DataType.createDate("yyyy-MM-dd hh:mm:ss"),
                        HierarchyBuilderDate.Granularity.MINUTE_HOUR_DAY_MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.HOUR_DAY_MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.DAY_MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.YEAR,
                        HierarchyBuilderDate.Granularity.DECADE,
                        HierarchyBuilderDate.Granularity.CENTURY
                )
        );
    }

    private static void generateRaceConceptIdInformation(String attributeName, Data data) {
        //Set the datatype
        data.getDefinition().setDataType(
                attributeName,
                DataType.INTEGER
        );

        //Set the attribute type
        data.getDefinition().setAttributeType(
                attributeName,
                AttributeType.QUASI_IDENTIFYING_ATTRIBUTE
        );
    }

    private static void generateBirthDateTimeInformation(String attributeName, Data data) {
        //Set the datatype
        data.getDefinition().setDataType(
                attributeName,
                DataType.createDate("yyyy-MM-dd hh:mm:ss")
        );

        //Set the attribute type
        data.getDefinition().setAttributeType(
                attributeName,
                AttributeType.QUASI_IDENTIFYING_ATTRIBUTE
        );
    }

    private static void generateGenderConceptIdInformation(String attributeName, Data data) {
        //Set the datatype
        data.getDefinition().setDataType(
                attributeName,
                DataType.INTEGER
        );

        //Set the attribute type
        data.getDefinition().setAttributeType(
                attributeName,
                AttributeType.QUASI_IDENTIFYING_ATTRIBUTE
        );
    }

    private static void generatePersonIdInformation(String attributeName, Data data) {
        //Set the datatype
        data.getDefinition().setDataType(
                attributeName,
                DataType.INTEGER
        );

        //Set the attribute type
        data.getDefinition().setAttributeType(
                attributeName,
                AttributeType.IDENTIFYING_ATTRIBUTE
        );
    }
}
