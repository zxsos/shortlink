package com.bang.shortlink.admin.service;

import com.bang.shortlink.admin.common.convention.result.Result;
import com.bang.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.bang.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 回收站接口层
 */

public interface RecycleBinService {
    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 分页短链接请求参数
     * @return 查询短链接响应
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
