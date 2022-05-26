package be.kul.scriptExecutor.Utils.DataSetAnonymization.HelperObjects;

import be.kul.scriptExecutor.Utils.Exceptions.DataSetAnonymizationException;
import org.deidentifier.arx.*;
import org.deidentifier.arx.aggregates.HierarchyBuilder;
import org.deidentifier.arx.aggregates.HierarchyBuilderDate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ArxColumnInformationConfigurer {
    public static void generateMap(List<String> attributeNames, Data data) throws IOException {
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
               default:
                   throw new DataSetAnonymizationException("No anonymization configuration for attribute: " + attributeName);
           }
       }
    }

    private static void generateRaceConceptIdInformation(String attributeName, Data data) throws IOException {
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

        //Create a hierarchy
        AttributeType.Hierarchy.DefaultHierarchy hierarchy = AttributeType.Hierarchy.create();
        hierarchy.add("0","{0, 8527, 8515, 8516}");
        hierarchy.add("8527","{0, 8527, 8515, 8516}");
        hierarchy.add("8515","{0, 8527, 8515, 8516}");
        hierarchy.add("8516","{0, 8527, 8515, 8516}");

        //Set the hierarchy
        data.getDefinition().setHierarchy(
                attributeName,
                hierarchy
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

        //Define the hierarchy builder
        HierarchyBuilder<Date> hierarchyBuilderForDates = HierarchyBuilderDate.create(
                DataType.createDate("yyyy-MM-dd hh:mm:ss"),
                HierarchyBuilderDate.Granularity.DAY_MONTH_YEAR,
                HierarchyBuilderDate.Granularity.MONTH_YEAR,
                HierarchyBuilderDate.Granularity.YEAR,
                HierarchyBuilderDate.Granularity.DECADE,
                HierarchyBuilderDate.Granularity.CENTURY
        );

        //Set the hierarchy
        data.getDefinition().setHierarchy(
                attributeName,
                hierarchyBuilderForDates
        );
    }

    private static void generateGenderConceptIdInformation(String attributeName, Data data) throws IOException {
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

        //Create a hierarchy
        AttributeType.Hierarchy.DefaultHierarchy hierarchy = AttributeType.Hierarchy.create();
        hierarchy.add("8507", "{8507,8532}");
        hierarchy.add("8532", "{8507,8532}");

        //Set the hierarchy
        data.getDefinition().setHierarchy(
                attributeName,
                hierarchy
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
