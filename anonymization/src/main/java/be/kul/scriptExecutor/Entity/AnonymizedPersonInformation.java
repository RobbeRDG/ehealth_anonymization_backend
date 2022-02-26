package be.kul.scriptExecutor.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;

public class AnonymizedPersonInformation {
    @JsonProperty("person_id")
    private long personId;

    @JsonProperty("anonymized_attributes")
    private HashMap<String,HashMap<String, String>> anonymizedAttributes;

    public AnonymizedPersonInformation(long personId) {
        this.personId = personId;
        this.anonymizedAttributes = new HashMap<>();
    }

    public AnonymizedPersonInformation(long personId, HashMap<String, HashMap<String, String>> anonymizedAttributes) {
        this.personId = personId;
        this.anonymizedAttributes = anonymizedAttributes;
    }

    public AnonymizedPersonInformation() {
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public HashMap<String, HashMap<String, String>> getAnonymizedAttributes() {
        return anonymizedAttributes;
    }

    public void setAnonymizedAttributes(HashMap<String, HashMap<String, String>> anonymizedAttributes) {
        this.anonymizedAttributes = anonymizedAttributes;
    }

    public void addAnonymizedAttribute(String attributeName, String anonymizationLevelIdentifier, String anonymizedValue) {
        if (anonymizedAttributes.containsKey(attributeName)) {
            //Update the hashmap
            HashMap<String,String> anonymizedAttributeValueMap = anonymizedAttributes.get(attributeName);
            anonymizedAttributeValueMap.put(anonymizationLevelIdentifier,anonymizedValue);
        } else {
            //Creat a new hashmap
            HashMap<String,String> anonymizedAttributeValueMap = new HashMap<>();
            anonymizedAttributeValueMap.put(anonymizationLevelIdentifier,anonymizedValue);
            anonymizedAttributes.put(attributeName,anonymizedAttributeValueMap);
        }
    }


}
