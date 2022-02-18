package be.kul.useraccess.Repository;

import be.kul.useraccess.Entity.ScriptExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScriptExecutionResultRepository extends MongoRepository<ScriptExecutionResult,String> {
    public ScriptExecutionResult findByScriptId(long scriptId);
}
