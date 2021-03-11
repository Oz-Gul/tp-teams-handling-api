package fr.sorbonne.paris.nord.university.api.entity;

import javax.persistence.*;

@Entity
@Table(name= "team")
public class TeamEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="slogan")
    private String slogan;

    public TeamEntity(){}

    public TeamEntity(Long id, String name, String slogan) {
        this.id = id;
        this.name = name;
        this.slogan = slogan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return "TeamEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slogan='" + slogan + '\'' +
                '}';
    }
}
