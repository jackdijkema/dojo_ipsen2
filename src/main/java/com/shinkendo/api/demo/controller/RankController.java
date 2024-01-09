package com.shinkendo.api.demo.controller;

import com.shinkendo.api.demo.dao.RankDao;
import com.shinkendo.api.demo.dto.RankResponseDTO;
import com.shinkendo.api.demo.mapper.RankMapper;
import com.shinkendo.api.demo.model.ApiResponse;
import com.shinkendo.api.demo.model.Rank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ranks")
@RequiredArgsConstructor
public class RankController {
    private final RankDao rankDao;
    private final RankMapper rankMapper;

    @GetMapping
    @ResponseBody
    public ApiResponse<List<RankResponseDTO>> all() {
        List<Rank> ranks = rankDao.findAll();
        List<RankResponseDTO> rankDtos = ranks.stream().map(rankMapper::fromEntity).toList();
        return new ApiResponse<>(rankDtos);
    }


}
