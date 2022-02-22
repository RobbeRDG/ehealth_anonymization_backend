package be.kul.scriptExecutor.Entity;


import be.kul.scriptExecutor.Utils.AnonymizedPersonInformationObjects.AnonymizedValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RedisHash("anonymized_person_information")
public class AnonymizedPersonInformation {
    @Id
    @JsonProperty("person_id")
    private long personId;

    @JsonProperty("anonymized_attributes")
    private HashMap<String,HashMap<String,AnonymizedValue>> anonymizedAttributes;

    public AnonymizedPersonInformation(long personId) {
        this.personId = personId;
        this.anonymizedAttributes = new HashMap<>();
    }


    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public HashMap<String, HashMap<String,AnonymizedValue>> getAnonymizedAttributes() {
        return anonymizedAttributes;
    }

    public void setAnonymizedAttributes(HashMap<String, HashMap<String,AnonymizedValue>> anonymizedAttributes) {
        this.anonymizedAttributes = anonymizedAttributes;
    }
}
