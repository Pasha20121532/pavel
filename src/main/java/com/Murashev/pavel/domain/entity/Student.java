package com.Murashev.pavel.domain.entity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Заполните поле ФИО")
    @Length(max = 100, message = "Поле неможет содержать более 100 символов")
    @Column(name = "name", length = 100)
    private String name;

    @NotBlank(message = "Заполните поле номера группы")
    @Length(max = 100, message = "Поле неможет содержать более 100 символов")
    @Column(name = "namegroup", length = 100)
    private String namegroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User studentuser;

    public String getStudentuser(){
        return studentuser != null ? studentuser.getUsername() : "отсутствует";
    }

    private String avatarname;

    public Student() {
        //groupp = " ";
    }//name = "безымянный";
    //}

    public Student(String name, String namegroup, User studentuser, String avatarname) {
        this.name = name;
        this.namegroup = namegroup;
        this.studentuser = studentuser;
        this.avatarname = avatarname;
    }

    public String getAvatarname() {
        return avatarname;
    }

    public void setAvatarname(String avatarname) {
        this.avatarname = avatarname;
    }

    public void setStudentuser(User studentuser) {
        this.studentuser = studentuser;
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

    public String getNamegroup() {
        return namegroup;
    }

    public void setNamegroup(String namegroup) {
        this.namegroup = namegroup;
    }
}


