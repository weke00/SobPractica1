/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author gerardca
 */
@Entity
@Table(name = "usuaris")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;
    private String idusuari;
    private String passwd;
    private String nom;
    private String idsession;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "followings", joinColumns = {
        @JoinColumn(name = "id_user", nullable = false, updatable = false)},
            inverseJoinColumns = {
        @JoinColumn(name = "following_user_id", nullable = false, updatable = false)})
    private List<Usuario> followings = new LinkedList<Usuario>();

    public Usuario() {
    }

    public Usuario(String idusuari, String passwd, String name, String idSession) {

        this.idusuari = idusuari;
        this.passwd = passwd;
        this.nom = name;
        this.idsession = idSession;
    }

    public String getIdsession() {
        return idsession;
    }

    public void setIdsession(String idsession) {
        this.idsession = idsession;
    }

    public long getId() {
        return user_id;
    }

    public void setId(long id) {
        this.user_id = id;
    }

    public String getIdusuari() {
        return idusuari;
    }

    public void setIdusuari(String idusuari) {
        this.idusuari = idusuari;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Usuario> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Usuario> followings) {
        this.followings = followings;
    }
}
