package com.moment.controller;

import com.moment.dto.LikeResponse;
import com.moment.dto.MomentDTO;
import com.moment.service.MomentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MomentController {

    private final MomentService momentService;

    @GetMapping("/featured")
    public MomentDTO getFeatured(@RequestHeader(value = "X-User-Id", defaultValue = "anonymous") String userId) {
        return momentService.getFeatured(userId);
    }

    @GetMapping("/moments")
    public List<MomentDTO> getMoments(@RequestHeader(value = "X-User-Id", defaultValue = "anonymous") String userId) {
        return momentService.getMoments(userId);
    }

    @GetMapping("/moments/{id}")
    public MomentDTO getMomentById(@PathVariable Long id,
                                   @RequestHeader(value = "X-User-Id", defaultValue = "anonymous") String userId) {
        return momentService.getMomentById(id, userId);
    }

    @PostMapping("/moments/{id}/like")
    public LikeResponse toggleLike(@PathVariable Long id,
                                   @RequestHeader(value = "X-User-Id", defaultValue = "anonymous") String userId) {
        return momentService.toggleLike(id, userId);
    }
}
