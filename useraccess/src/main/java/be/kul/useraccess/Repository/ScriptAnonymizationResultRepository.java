package be.kul.useraccess.Repository;

import be.kul.useraccess.Entity.ScriptAnonymizationResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScriptAnonymizationResultRepository extends MongoRepository<ScriptAnonymizationResult,String> {
    public ScriptAnonymizationResult findByScriptId(long scriptId);
}
