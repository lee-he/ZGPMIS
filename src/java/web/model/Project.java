/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "project")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.findByOwner", query = "SELECT p FROM Project p WHERE p.owner = :owner"),
    @NamedQuery(name = "Project.findByBudget", query = "SELECT p FROM Project p WHERE p.budget = :budget"),
    @NamedQuery(name = "Project.findByDuedate", query = "SELECT p FROM Project p WHERE p.duedate = :duedate"),
    @NamedQuery(name = "Project.findByTel", query = "SELECT p FROM Project p WHERE p.tel = :tel"),
    @NamedQuery(name = "Project.findByAddress", query = "SELECT p FROM Project p WHERE p.address = :address")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "owner")
    private String owner;
    @Column(name = "budget")
    private Integer budget;
    @Column(name = "duedate")
    @Temporal(TemporalType.DATE)
    private Date duedate;
    @Column(name = "tel")
    private String tel;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, String owner, String address) {
        this.name = name;
        this.owner = owner;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.model.Project[ name=" + name + " ]";
    }
}
