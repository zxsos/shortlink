package com.bang.shortlink.admin.test;

public class UserTableShardingTest {
    public static String SQL = "CREATE TABLE `t_link_%d`  (\n" +
            "  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `domain` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '域名',\n" +
            "  `short_uri` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '短链接',\n" +
            "  `full_short_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '完整短链接',\n" +
            "  `origin_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原始链接',\n" +
            "  `click_num` int NOT NULL DEFAULT 0 COMMENT '点击量',\n" +
            "  `enable_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '启用标识 0:禁用 1:启用',\n" +
            "  `created_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '创建类型 0:后台创建 1:用户创建',\n" +
            "  `valid_date_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '有效期类型 0:永久有效 1:指定日期失效',\n" +
            "  `valid_date` datetime NOT NULL COMMENT '有效期',\n" +
            "  `describe` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',\n" +
            "  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0:未删除 1:已删除',\n" +
            "  `gid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组标识',\n" +
            "  `favicon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '图标',\n" +
            "  PRIMARY KEY (`id`) USING BTREE,\n" +
            "  UNIQUE INDEX `idx_unique_short_uri`(`short_uri` ASC) USING BTREE\n" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1885747054401138691 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '短链接表' ROW_FORMAT = DYNAMIC;";
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL)+"%n", i);
        }
    }
}
