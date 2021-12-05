package be.kul.scriptExecutor.Repository;

import be.kul.scriptExecutor.Utils.Exceptions.DataSetQueryExecutionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MedicalDataRepository{
    @PersistenceContext
    private EntityManager entityManager;

    private ObjectMapper mapper = new ObjectMapper();


    public List<HashMap<String, String>> executeQueryResponse(String queryString) {
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
                    data.put(entry.getKey(), mapper.writeValueAsString(entry.getValue()));
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
