package com.moment.dto;

import com.moment.entity.Moment;
import lombok.Data;

@Data
public class MomentDTO {

    private Long id;
    private String date;
    private String imageUrl;
    private String quote;
    private String source;
    private String description;
    private boolean liked;
    private int likeCount;

    public static MomentDTO from(Moment moment, boolean liked) {
        MomentDTO dto = new MomentDTO();
        dto.setId(moment.getId());
        dto.setDate(moment.getDate());
        dto.setImageUrl(moment.getImageUrl());
        dto.setQuote(moment.getQuote());
        dto.setSource(moment.getSource());
        dto.setDescription(moment.getDescription());
        dto.setLiked(liked);
        dto.setLikeCount(moment.getLikeCount());
        return dto;
    }
}
