package be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("exception_data")
public class ExceptionData extends Data{
    @JsonProperty("exception_type")
    private String exceptionType;

    @JsonProperty("exception_message")
    private String exceptionMessage;

    public ExceptionData(String exceptionType, String exceptionMessage) {
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
