package be.kul.scriptExecutor.Repository;

import be.kul.scriptExecutor.Entity.AnonymizedPersonInformation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnonymizedPersonRepository extends CrudRepository<AnonymizedPersonInformation, String> {}
