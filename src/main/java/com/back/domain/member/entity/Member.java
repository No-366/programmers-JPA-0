package com.back.domain.member.entity;


import com.back.global.initData.BaseInitData;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {
    private String username;
    private String nickname;

    public Member(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
