package by.itechart.flowerty.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: Мария
 * Date: 19.03.15
 * Time: 22:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN", length = 20, nullable = false)
    private String login;

    @Column(name = "PASSWORD", length = 20, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @OneToOne(mappedBy = "user")
    private Contact contact;

}
