package com.Murashev.pavel.domain.entity;
import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "groupp")
    private Integer groupp;
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "avatarname")
    private String avatarname;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "firstname_id")
    private Firstname firstname;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "secondname_id")
    private Secondname secondname;

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = {CascadeType.ALL})
    @JoinColumn(name = "lastname_id")
    private Lastname lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User studentuser;

    public String getStudentuser(){
        return studentuser != null ? studentuser.getUsername(): "отсутствует";
    }

    public String getName() {

        return lastname.getName() + " " + firstname.getName() + " " + secondname.getName();
    }

    public Student() {
    }

    public Student(Firstname firstname, Secondname secondname, Lastname lastname, Integer groupp,
                   User studentuser, String avatarname) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.lastname = lastname;
        this.groupp = groupp;
        this.studentuser = studentuser;
        this.avatarname = avatarname;
    }

    public Firstname getFirstname() {
        return firstname;
    }
    public void setFirstname(Firstname firstname) {
        this.firstname = firstname;
    }
    public Secondname getSecondname() {
        return secondname;
    }
    public void setSecondname(Secondname secondname) {
        this.secondname = secondname;
    }
    public Lastname getLastname() {
        return lastname;
    }
    public void setLastname(Lastname lastname) {
        this.lastname = lastname;
    }
    public String getAvatarname(){
        return avatarname;
    }
    public void setAvatarname(String avatarname) {
        this.avatarname = avatarname;
    }
    public void setStudentuser(User studentuser) {
        this.studentuser = studentuser;
    }
    public Integer getGroup(){ return groupp; }
    public void setGroup(Integer groupp){ this.groupp = groupp; }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}

