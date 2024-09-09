package KangRak.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass  // 공통 속성 상속을 위해 사용
@EntityListeners(AuditingEntityListener.class)  // Auditing 활성화
@Data
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)  // 생성일은 수정할 수 없도록 설정
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
