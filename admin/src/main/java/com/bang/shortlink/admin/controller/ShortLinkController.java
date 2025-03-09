package com.bang.shortlink.admin.controller;

import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.convention.result.Results;
import com.bang.shortlink.admin.remote.ShortLinkActualRemoteService;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkBatchCreateReqDTO;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkBaseInfoRespDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkBatchCreateRespDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.bang.shortlink.admin.toolkit.EasyExcelWebUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 短链接后管控制层
 */
@RestController(value = "shortLinkControllerByAdmin")
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkActualRemoteService shortLinkActualRemoteService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/shortlink/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkActualRemoteService.createShortLink(requestParam);
    }

    /**
     * 批量创建短链接
     */
    @SneakyThrows
    @PostMapping("/api/shortlink/admin/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkActualRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String formattedDate = sdf.format(new Date());
            EasyExcelWebUtil.write(response, "批量创建短链接-" + formattedDate, ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }

    /**
     * 修改短链接
     */
    @PostMapping("/api/shortlink/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkActualRemoteService.updateShortLink(requestParam);
        return Results.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/shortlink/admin/v1/page")
    public Result<Page<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkActualRemoteService.pageShortLink(requestParam.getGid(), requestParam.getOrderTag(), requestParam.getCurrent(), requestParam.getSize());
    }
}
