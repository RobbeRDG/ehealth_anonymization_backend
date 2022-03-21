package be.kul.scriptExecutor.Repository;

import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.ScriptSummaryComponents.ContainedData.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetQueryExecutionException;
import be.kul.scriptExecutor.Utils.Enums.AnonymizationStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicalDataRepository{
    @PersistenceContext
    private EntityManager entityManager;
    private final ObjectMapper mapper = new ObjectMapper()
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                    true);


    public List<HashMap<String,String>> executeQueryResponse(String queryString) {
        //Set the query
        Query query = entityManager.createNativeQuery(queryString)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE); //Is deprecated but no decent alternative for now

        //Execute the query
        List<Map<String,Object>> resultList = query.getResultList();

        List<HashMap<String,String>> dataSet = new ArrayList<>();
        for (Map<String, Object> result : resultList) {
            //Create a new data map for the dataset
            HashMap<String, String> data = new HashMap<>();

            //Iterate over the entries of the result and put them in the data map
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                try {
                    if (entry.getValue() instanceof Timestamp) {
                        String dateTimeString = mapper.writeValueAsString(entry.getValue()).replaceAll("^\"|\"$", "");
                        data.put(entry.getKey(), dateTimeString);
                    } else {
                        data.put(entry.getKey(), mapper.writeValueAsString(entry.getValue()));
                    }
                } catch (JsonProcessingException e) {
                    throw new DataSetQueryExecutionException("Couldn't convert input data object to string");
                }
            }

            //Add the data map to the dataSet
            dataSet.add(data);
        }

        return dataSet;
    }

}
