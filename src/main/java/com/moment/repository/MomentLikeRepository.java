package com.moment.repository;

import com.moment.entity.MomentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MomentLikeRepository extends JpaRepository<MomentLike, Long> {

    boolean existsByMomentIdAndUserId(Long momentId, String userId);

    void deleteByMomentIdAndUserId(Long momentId, String userId);
}
