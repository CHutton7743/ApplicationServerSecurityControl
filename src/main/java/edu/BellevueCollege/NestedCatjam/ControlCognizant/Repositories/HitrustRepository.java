package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.HitrustControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface HitrustRepository extends JpaRepository<HitrustControl, Long> {
    HitrustControl findById(long id);
    HitrustControl findByControlName(String controlName);
    Set<HitrustControl> findAllBySatisfied(boolean isSatisfied);
    Set<HitrustControl> findAllByControlCategory(String controlCategory);
    Set<HitrustControl> findAllByControlFunction(String controlFunction);
}