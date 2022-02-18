package be.kul.useraccess.Utils.ScriptSummaryComponents.ContainedData.DataClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("anonymized_dataset_data")
public class AnonymizedDataSetData extends Data {
    @JsonProperty("anonymized_dataset")
    private List<HashMap<String, String>> anonymizedDataSet;


}
