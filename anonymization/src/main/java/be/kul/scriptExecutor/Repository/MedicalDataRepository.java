package be.kul.scriptExecutor.Repository;

import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.AnonymizationInformation.AnonymizationInformation;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataClasses.DataSetData;
import be.kul.scriptExecutor.Utils.Components.ScriptExecution.Data.DataContainer.DataContainer;
import be.kul.scriptExecutor.Utils.Exceptions.DataSetQueryExecutionException;
import be.kul.scriptExecutor.Utils.enums.AnonymizationStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MedicalDataRepository{
    @PersistenceContext
    private EntityManager entityManager;

    private ObjectMapper mapper = new ObjectMapper()
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES,
                    true);


    public DataContainer executeQueryResponse(String queryString) {
        //Set the query
        Query query = entityManager.createNativeQuery(queryString);
        NativeQueryImpl nativeQuery = (NativeQueryImpl) query;

        //Execute the query
        nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String,Object>> resultList = nativeQuery.getResultList();

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

        //Build the dataset object
        DataSetData dataSetData = new DataSetData(dataSet);

        //Build the anonymization information
        AnonymizationInformation anonymizationInformation = new AnonymizationInformation(AnonymizationStatus.UNANONYMIZED, null);

        //Build the data container
        DataContainer dataContainer = new DataContainer();
        dataContainer.setAssignedData(dataSetData);
        dataContainer.setAnonymizationInformation(anonymizationInformation);

        return dataContainer;
    }

}
