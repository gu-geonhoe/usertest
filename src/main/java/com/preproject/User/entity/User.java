package com.preproject.User.entity;



import com.preproject.audit.Auditable;
import lombok.*;


import javax.persistence.*;

@NoArgsConstructor

@Getter
@Setter
@Entity
@Data
@Table(name = "USERS")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(length = 15, nullable = false, unique = true)
    private String userName;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    //private long questionId;

    public User(long userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
//
//    public void setQuestion(Question question){
//        //질문 추가 기능
//    }
//
//    public void setAnswer(Answer answer){
//        //답글 추가 기능
//    }
//
//    public  void setComment(Comment comment){
//        //댓글 추가 기능
//    }



}
