package be.kul.scriptExecutor.Repository;

import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AnonymizedPersonRepository {
    ObjectMapper objectMapper = new ObjectMapper();
    private HashOperations<String, String, String> hashOperations;

    @Autowired
    public AnonymizedPersonRepository(StringRedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public AnonymizedPersonInformation findById(String personId) {
        //Get the json string of the anonymized person informatoin
        String anonymizedPersonInformationString = hashOperations.get("anonymizedPersonInformation" , personId);

        //Convert to object
        AnonymizedPersonInformation anonymizedPersonInformation = null;
        try {
            anonymizedPersonInformation = objectMapper.readValue(anonymizedPersonInformationString, AnonymizedPersonInformation.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            //throw new RuntimeException("Error reading anonymized person information json: This shouldn't happen");
        }

        return anonymizedPersonInformation;
    }
}
