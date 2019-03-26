package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Institution;
import pl.coderslab.entity.InstitutionListOfWhomHelp;
import pl.coderslab.entity.InstitutionLocation;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    Institution findFirstByName (String name);

    List<Institution> findAllByNameContainingIgnoreCase(String name);
    List<Institution> findAllByInstitutionLocations(List<InstitutionLocation> institutionLocations);
    List<Institution> findAllByWhomHelp(List<InstitutionListOfWhomHelp> institutionListOfWhomHelp);

    List<Institution> findAllByWhomHelpAndInstitutionLocations(List<InstitutionLocation> institutionLocations,
                                       List<InstitutionListOfWhomHelp> institutionListOfWhomHelp);

}
