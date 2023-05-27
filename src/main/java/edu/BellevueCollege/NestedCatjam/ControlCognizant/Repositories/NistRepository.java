package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.NistControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NistRepository extends JpaRepository<NistControl, Long> {
    NistControl findById(long id);
    NistControl findByControlName(String controlName);
    Set<NistControl> findAllBySatisfied(boolean isSatisfied);
    Set<NistControl> findAllByControlCategory(String controlCategory);
    Set<NistControl> findAllByControlFunction(String controlFunction);
}
