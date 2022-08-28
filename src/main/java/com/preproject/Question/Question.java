package com.preproject.Question;


import com.preproject.Tag.Tag;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;
    private String title;
    private String content;
    private String userName;
//    private Tag tag;
    private LocalDateTime createdAt = LocalDateTime.now();



    }
    //작성 시간 createAt

