package zw.co.afrosoft.myblog.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "user_name",unique = true)
    private String username;
    @Column(name = "email",unique = true)
    private String email;
    private String password;
}
