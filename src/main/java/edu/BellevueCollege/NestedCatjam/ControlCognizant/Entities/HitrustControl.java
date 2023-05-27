package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.NistRepository;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "hitrust_controls")
@Data
public class HitrustControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hitrust_id")
    private long id;

    @Column(name = "control_function")
    @NonNull
    private String controlFunction;

    @Column(name = "control_category")
    @NonNull
    private String controlCategory;

    @Column(name = "control_name")
    @NonNull
    private String controlName;

    @Column(name = "control_description")
    @NonNull
    private String controlDescription;

    @Column(name = "is_satisfied")
    private boolean satisfied;

    @ManyToMany(mappedBy = "hitrustControls")
    private Set<NistControl> nistControls = new HashSet<>();

    public HitrustControl(String controlFunction, String controlCategory, String controlName, String controlDescription) {
        this.controlFunction = controlFunction;
        this.controlCategory = controlCategory;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.satisfied = false;
    }
    public HitrustControl(String controlFunction, String controlCategory, String controlName, String controlDescription, Set<NistControl> mappings) {
        this.controlFunction = controlFunction;
        this.controlCategory = controlCategory;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.satisfied = false;
        this.nistControls = mappings;
    }

    public HitrustControl() {
    }
}
