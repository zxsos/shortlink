package com.bang.shortlink.admin.controller;

import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.common.convention.result.Results;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.bang.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.bang.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.bang.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 短链接分组控制层
 */
@RestController()
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增短链接分组
     *
     * @param requestParam
     * @return
     */
    @PostMapping("/api/shortlink/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询用户分组
     *
     * @return
     */

    @GetMapping("/api/shortlink/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改用户分组
     */
    @PutMapping("/api/shortlink/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }

    /**
     * 删除短链接分组
     */
    @DeleteMapping("/api/shortlink/admin/v1/group")
    public Result<Void> updateGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }
    /**
     * 用户分组排序
     */
    @PostMapping("group/sort")
    public Result<Void> updateGroup(@RequestBody List<ShortLinkGroupSortReqDTO> requestParam) {
        groupService.sortGroup(requestParam);
        return Results.success();
    }
}