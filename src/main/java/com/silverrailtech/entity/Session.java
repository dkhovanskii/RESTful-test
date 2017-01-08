package com.silverrailtech.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "browserid")
    private String browserId;

    @Column(name = "state")
    private String state;

    @Column(name = "accesses")
    private int access;

    @Column(name = "firstaccess")
    private Date firstAccess;

    @Column(name = "lastaccess")
    private Date lastAccess;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrowserId() {
        return browserId;
    }

    public void setBrowserId(String broserId) {
        this.browserId = broserId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public Date getFirstAccess() {
        return firstAccess;
    }

    public void setFirstAccess(Date firstAccess) {
        this.firstAccess = firstAccess;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
}
