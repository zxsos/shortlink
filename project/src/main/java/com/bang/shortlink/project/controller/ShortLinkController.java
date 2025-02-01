package com.bang.shortlink.project.controller;

import com.bang.shortlink.project.common.convention.result.Result;
import com.bang.shortlink.project.common.convention.result.Results;
import com.bang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.bang.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ShortLinkController {
    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/shortlink/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }
}
