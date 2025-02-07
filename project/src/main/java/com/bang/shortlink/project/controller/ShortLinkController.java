package com.bang.shortlink.project.controller;

import com.bang.shortlink.project.common.convention.result.Result;
import com.bang.shortlink.project.common.convention.result.Results;
import com.bang.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.bang.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.bang.shortlink.project.service.ShortLinkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    /**
     * 分页
     */
    @GetMapping("/api/shortlink/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
    /**
     * 查询分组内数量
     */
    @GetMapping("/api/shortlink/v1/count")
    public  Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount( @RequestParam("requestParam") List<String> requestParam)
    {
        return Results.success(shortLinkService.listGroupShortLinkCount(requestParam));
    }
}
