package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "evidence")
@Data
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evidence_id")
    private long id;

    @Column(name = "evidence_name")
    private String name;

    @Column(name = "evidence_description")
    private String description;

    @Column(name = "evidence_type")
    private String type;

    @Column(name = "evidence_file")
    String base64;

    @Column(name = "contributor_auth0_id")
    private String contributorAuth0ID;

    @Column(name = "nist_control_id")
    private long nistControlId;

    @Column(name = "chat_id")
    private String chatid;

    @Column(name = "organization_id")
    private String organizationID;

    public Evidence() {
    }

    public Evidence(String name, String description, String type, String base64,
                    String contributorAuth0ID, long nistControlId, String chatid, String organizationID) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.base64 = base64;
        this.contributorAuth0ID = contributorAuth0ID;
        this.nistControlId = nistControlId;
        this.chatid = chatid;
        this.organizationID = organizationID;
    }
}