package com.bang.shortlink.project.service;

import com.bang.shortlink.project.dao.entity.ShortLinkDO;
import com.bang.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.bang.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.bang.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.bang.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 回收站管理
 */
public interface RecycleBinService extends IService<ShortLinkDO> {
    /**
     * 保存回收站
     *
     * @param requestParam 请求参数
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询回收站
     * @param requestParam 分页查询请求参数
     * @return 分页返回结果
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    /**
     * 恢复短链接
     * @param requestParam 恢复短链接请求参数
     */
    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);
}
