package be.kul.useraccess.Repository;

import be.kul.useraccess.Entity.ScriptSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScriptSummaryRepository extends MongoRepository<ScriptSummary,String> {
    public ScriptSummary findScriptSummaryByScriptId(long scriptId);
}
