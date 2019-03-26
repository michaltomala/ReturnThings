package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.entity.InstitutionLocation;

import java.util.List;
import java.util.Set;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Institution findFirstByName (String name);

    Set<Institution> findAllByNameContainingIgnoreCase(String name);
    Set<Institution> findAllByInstitutionLocations(List<InstitutionLocation> institutionLocations);
    Set<Institution> findAllByWhomHelpIn(List<InstitutionListOfWhomHelp> institutionListOfWhomHelp);

    Set<Institution> findAllByInstitutionLocationsAndWhomHelpIn(List<InstitutionLocation> institutionLocations,
                                       List<InstitutionListOfWhomHelp> institutionListOfWhomHelp);

}
