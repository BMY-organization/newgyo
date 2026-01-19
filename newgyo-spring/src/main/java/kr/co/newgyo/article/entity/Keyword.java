package kr.co.newgyo.article.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name="KEYWORD")
public class Keyword {
    @Id
    @Column(name="KEYWORD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;
}
