package ca.jackzavarella.courses.api.repositories;

import ca.jackzavarella.courses.api.models.Institution;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
public interface InstitutionRepository extends MongoRepository<Institution, String> {
    Institution findInstitutionByName(String name);
}
