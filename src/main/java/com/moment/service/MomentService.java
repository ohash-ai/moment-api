package com.moment.service;

import com.moment.dto.LikeResponse;
import com.moment.dto.MomentDTO;
import com.moment.entity.Moment;
import com.moment.entity.MomentLike;
import com.moment.repository.MomentLikeRepository;
import com.moment.repository.MomentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MomentService {

    private final MomentRepository momentRepository;
    private final MomentLikeRepository momentLikeRepository;

    public MomentDTO getFeatured(String userId) {
        Moment moment = momentRepository.findFirstByIsFeaturedTrue()
                .orElseThrow(() -> new RuntimeException("No featured moment found"));
        boolean liked = momentLikeRepository.existsByMomentIdAndUserId(moment.getId(), userId);
        return MomentDTO.from(moment, liked);
    }

    public List<MomentDTO> getMoments(String userId) {
        return momentRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(m -> MomentDTO.from(m, momentLikeRepository.existsByMomentIdAndUserId(m.getId(), userId)))
                .toList();
    }

    public MomentDTO getMomentById(Long id, String userId) {
        Moment moment = momentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moment not found: " + id));
        boolean liked = momentLikeRepository.existsByMomentIdAndUserId(id, userId);
        return MomentDTO.from(moment, liked);
    }

    @Transactional
    public LikeResponse toggleLike(Long id, String userId) {
        Moment moment = momentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moment not found: " + id));

        boolean alreadyLiked = momentLikeRepository.existsByMomentIdAndUserId(id, userId);
        if (alreadyLiked) {
            momentLikeRepository.deleteByMomentIdAndUserId(id, userId);
            moment.setLikeCount(Math.max(0, moment.getLikeCount() - 1));
        } else {
            MomentLike like = new MomentLike();
            like.setMomentId(id);
            like.setUserId(userId);
            momentLikeRepository.save(like);
            moment.setLikeCount(moment.getLikeCount() + 1);
        }
        momentRepository.save(moment);

        return new LikeResponse(!alreadyLiked, moment.getLikeCount());
    }
}
