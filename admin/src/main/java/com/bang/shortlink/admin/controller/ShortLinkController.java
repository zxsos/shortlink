package com.bang.shortlink.admin.controller;

import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.convention.result.Results;
import com.bang.shortlink.admin.remote.ShortLinkRemoteService;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;

/**
 * 短链接后管控制层
 */
@RestController
public class ShortLinkController {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     */
    @PostMapping("/api/shortlink/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 修改短链接
     */
    @PostMapping("/api/shortlink/admin/v1/update")
    public Result<Void> updateSHortLink(@RequestBody ShortLinkUpdateReqDTO requestParam)
    {
         shortLinkRemoteService.updateShortLink(requestParam);
         return Results.success();
    }
    /**
     * 分页查询短链接
     */
    @GetMapping("/api/shortlink/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }
}
