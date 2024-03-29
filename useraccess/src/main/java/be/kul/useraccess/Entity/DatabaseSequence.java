package be.kul.useraccess.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;

    public long getSeq() {
        return seq;
    }
}
