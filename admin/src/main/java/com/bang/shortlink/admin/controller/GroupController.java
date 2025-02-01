package com.bang.shortlink.admin.controller;

import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.convention.result.Results;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTo;
import com.bang.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.bang.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTo requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    @GetMapping("/api/shortlink/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup()
    {
        return Results.success(groupService.listGroup());
    }


}