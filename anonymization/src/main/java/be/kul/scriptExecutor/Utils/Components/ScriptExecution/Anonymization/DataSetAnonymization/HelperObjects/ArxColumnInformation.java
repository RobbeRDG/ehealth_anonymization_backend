package be.kul.scriptExecutor.Utils.Components.ScriptExecution.Anonymization.DataSetAnonymization.HelperObjects;

import org.deidentifier.arx.AttributeType;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.aggregates.HierarchyBuilder;
import org.deidentifier.arx.aggregates.HierarchyBuilderDate;

import java.util.Date;

public class ArxColumnInformation {
    private String columnName;
    private DataType dataType;
    private AttributeType attributeType;
    private AttributeType.Hierarchy hierarchy;

    public ArxColumnInformation(String columnName) {
        this.columnName = columnName;
    }


    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public void setHierarchy(AttributeType.Hierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public String getColumnName() {
        return columnName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public AttributeType.Hierarchy getHierarchy(String[] data) {
        if (hierarchy != null) {
            return hierarchy;
        } else {
            if (dataType instanceof DataType.ARXDate) {
                //Set the datetime hierarchy
                HierarchyBuilder<Date> dateTimeHierarchy = HierarchyBuilderDate.create(
                        DataType.createDate("yyyy-MM-dd hh:mm:ss"),
                        HierarchyBuilderDate.Granularity.MINUTE_HOUR_DAY_MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.HOUR_DAY_MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.DAY_MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.MONTH_YEAR,
                        HierarchyBuilderDate.Granularity.YEAR,
                        HierarchyBuilderDate.Granularity.DECADE,
                        HierarchyBuilderDate.Granularity.CENTURY
                );

                return dateTimeHierarchy.build(data);
            } else {
                return null;
            }
        }
    }
}
