package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nist_controls")
@Data
public class NistControl {
    public NistControl() {
    }

    @Column(name = "nist_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "mappings",
            joinColumns = @JoinColumn(name = "nist_control_id"),
            inverseJoinColumns = @JoinColumn(name = "hitrust_control_id")
    )
    private Set<HitrustControl> hitrustControls = new HashSet<>();

    @Column(name = "is_satisfied")
    private boolean satisfied;

    public NistControl(String controlFunction, String controlCategory, String controlName, String controlDescription) {
        this.controlFunction = controlFunction;
        this.controlCategory = controlCategory;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.satisfied = false;
    }

    public NistControl(String controlFunction, String controlCategory, String controlName, String controlDescription, Set<HitrustControl> hitrustMappings) {
        this.controlFunction = controlFunction;
        this.controlCategory = controlCategory;
        this.controlName = controlName;
        this.controlDescription = controlDescription;
        this.satisfied = false;
        this.hitrustControls = hitrustMappings;
    }
}
