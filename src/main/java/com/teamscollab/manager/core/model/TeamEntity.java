package com.teamscollab.manager.core.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team", schema = "public")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "acron", length = 10)
    private String acron;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcron() {
        return acron;
    }

    public void setAcron(String acron) {
        this.acron = acron;
    }

}
