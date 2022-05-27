/*
 Navicat Premium Data Transfer

 Source Server         : 开发环境
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 192.168.5.110:3306
 Source Schema         : saas_supplier

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 27/05/2022 11:52:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for supplier_basic_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_basic_info`;
CREATE TABLE `supplier_basic_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `source` int NULL DEFAULT NULL COMMENT '来源',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商简称',
  `supplier_size_id` bigint NULL DEFAULT NULL COMMENT '公司规模对应业务字典ID',
  `manage_type_id` bigint NULL DEFAULT NULL COMMENT '经营类型对应业务字典ID',
  `company_nature_id` bigint NULL DEFAULT NULL COMMENT '公司性质对应业务字典ID',
  `sales_year` decimal(10, 0) NULL DEFAULT NULL COMMENT '每年销售额(万元)',
  `business_scope` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经营范围',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公司简介',
  `self_support_type` int NULL DEFAULT NULL COMMENT '自营状态',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_basic_info_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_basic_info_log`;
CREATE TABLE `supplier_basic_info_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `source` int NULL DEFAULT NULL COMMENT '来源',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商编码',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商简称',
  `supplier_size_id` bigint NULL DEFAULT NULL COMMENT '公司规模对应业务字典ID',
  `manage_type_id` bigint NULL DEFAULT NULL COMMENT '经营类型对应业务字典ID',
  `company_nature_id` bigint NULL DEFAULT NULL COMMENT '公司性质对应业务字典ID',
  `sales_year` decimal(10, 0) NULL DEFAULT NULL COMMENT '每年销售额(万元)',
  `business_scope` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经营范围',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公司简介',
  `self_support_type` int NULL DEFAULT NULL COMMENT '自营状态',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商基础信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_business_history
-- ----------------------------
DROP TABLE IF EXISTS `supplier_business_history`;
CREATE TABLE `supplier_business_history`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int NULL DEFAULT NULL COMMENT '所属类型',
  `business_id` bigint NULL DEFAULT NULL COMMENT '业务主键ID',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `old_business_status` int NULL DEFAULT NULL COMMENT '变更前业务状态',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商业务状态变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_buyer_user
-- ----------------------------
DROP TABLE IF EXISTS `supplier_buyer_user`;
CREATE TABLE `supplier_buyer_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `buyer_user_id` int NULL DEFAULT NULL COMMENT '所属采购员',
  `old_buyer_user_id` int NULL DEFAULT NULL COMMENT '原所属采购员',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商所属采购员变更记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_buyer_user_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_buyer_user_log`;
CREATE TABLE `supplier_buyer_user_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `buyer_user_id` int NULL DEFAULT NULL COMMENT '所属采购员',
  `old_buyer_user_id` int NULL DEFAULT NULL COMMENT '原所属采购员',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商所属采购员变更记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_check_opinion
-- ----------------------------
DROP TABLE IF EXISTS `supplier_check_opinion`;
CREATE TABLE `supplier_check_opinion`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int NULL DEFAULT NULL COMMENT '类型',
  `business_id` bigint NULL DEFAULT NULL COMMENT '业务主键ID',
  `assignee_id` bigint NULL DEFAULT NULL COMMENT '负责人id',
  `opinion_type` int NULL DEFAULT NULL COMMENT '处理结果',
  `opinion` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批意见',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_complaint_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_complaint_info`;
CREATE TABLE `supplier_complaint_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `user_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `deal_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '处理备注',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投诉信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_contract_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_contract_info`;
CREATE TABLE `supplier_contract_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `start_date` datetime NULL DEFAULT NULL COMMENT '生效日期',
  `end_data` datetime NULL DEFAULT NULL COMMENT '截至日期',
  `start_sign_date` datetime NULL DEFAULT NULL COMMENT '起签日期',
  `signature_date` datetime NULL DEFAULT NULL COMMENT '签章日期',
  `signature_user` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签章人',
  `upload_file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传合同附件',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '合同信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_dict
-- ----------------------------
DROP TABLE IF EXISTS `supplier_dict`;
CREATE TABLE `supplier_dict`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码(作用域)',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商ID',
  `dict_id` bigint NULL DEFAULT NULL COMMENT '业务字典ID',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商业务字典关联关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_dict_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_dict_info`;
CREATE TABLE `supplier_dict_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级id 一级为0',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据名称',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'org模块数据字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for supplier_dict_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_dict_log`;
CREATE TABLE `supplier_dict_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码(作用域)',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商ID',
  `dict_id` bigint NULL DEFAULT NULL COMMENT '业务字典ID',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商业务字典关联关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_industry_qualification
-- ----------------------------
DROP TABLE IF EXISTS `supplier_industry_qualification`;
CREATE TABLE `supplier_industry_qualification`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `type_id` bigint NULL DEFAULT NULL COMMENT '资质类型ID',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资质证件',
  `start_date` datetime NULL DEFAULT NULL COMMENT '资质有效期开始',
  `end_date` datetime NULL DEFAULT NULL COMMENT '资质有效期结束',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '行业资质' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_offline_achievement
-- ----------------------------
DROP TABLE IF EXISTS `supplier_offline_achievement`;
CREATE TABLE `supplier_offline_achievement`  (
  `id` bigint NOT NULL COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `project_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工程名称',
  `project_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工程地址',
  `cooperation_unit` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合作单位',
  `contract_amount` decimal(10, 0) NULL DEFAULT NULL COMMENT '合同金额',
  `cooperation_start_date` datetime NULL DEFAULT NULL COMMENT '合作周期开始时间',
  `cooperation_end_date` datetime NULL DEFAULT NULL COMMENT '合作周期结束时间',
  `user_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `user_phone` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `file_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传文件路径',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '线下业绩' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_out_line
-- ----------------------------
DROP TABLE IF EXISTS `supplier_out_line`;
CREATE TABLE `supplier_out_line`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商ID',
  `type_id` int NULL DEFAULT NULL COMMENT '违规类型ID',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '违规原因',
  `business_status` int NULL DEFAULT NULL COMMENT '业务状态',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商违规信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_qualification_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_qualification_info`;
CREATE TABLE `supplier_qualification_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `credit_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `is_long_time` tinyint NULL DEFAULT NULL COMMENT '营业执照是否长期',
  `business_license_date` datetime NULL DEFAULT NULL COMMENT '营业执照有效期至',
  `legal_person_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人名称',
  `legal_person_certificate_id` bigint NULL DEFAULT NULL COMMENT '法人证件类型对应业务字典ID',
  `legal_person_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人证件号码',
  `bank_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `is_general_taxpayer` tinyint NULL DEFAULT NULL COMMENT '是否为一般纳税人',
  `taxpayer_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `is_three_in_one` tinyint NULL DEFAULT NULL COMMENT '是否三证合一',
  `general_taxpayer_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一般纳税人资格证书url',
  `business_license_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照电子版url',
  `tax_registration_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税务登记证 url',
  `bank_info_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行信息电子版url',
  `organization_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码证url',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商资质信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_qualification_info_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_qualification_info_log`;
CREATE TABLE `supplier_qualification_info_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `credit_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `is_long_time` tinyint NULL DEFAULT NULL COMMENT '营业执照是否长期',
  `business_license_date` datetime NULL DEFAULT NULL COMMENT '营业执照有效期至',
  `legal_person_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人名称',
  `legal_person_certificate_id` bigint NULL DEFAULT NULL COMMENT '法人证件类型对应业务字典ID',
  `legal_person_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人证件号码',
  `bank_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账号',
  `is_general_taxpayer` tinyint NULL DEFAULT NULL COMMENT '是否为一般纳税人',
  `taxpayer_number` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `is_three_in_one` tinyint NULL DEFAULT NULL COMMENT '是否三证合一',
  `general_taxpayer_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一般纳税人资格证书url',
  `business_license_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照电子版url',
  `tax_registration_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税务登记证 url',
  `bank_info_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行信息电子版url',
  `organization_code_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码证url',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商资质信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_register_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_register_info`;
CREATE TABLE `supplier_register_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `registered_capital` decimal(20, 0) NULL DEFAULT NULL COMMENT '注册资金(万元)',
  `registered_date` datetime NULL DEFAULT NULL COMMENT '公司注册成立日期',
  `province_id` bigint NULL DEFAULT NULL COMMENT '注册地区id(省)',
  `city_id` bigint NULL DEFAULT NULL COMMENT '注册地区ID(市)',
  `district_id` bigint NULL DEFAULT NULL COMMENT '注册地区ID(区)',
  `registered_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细注册地址',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商注册信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_register_info_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_register_info_log`;
CREATE TABLE `supplier_register_info_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `registered_capital` decimal(20, 0) NULL DEFAULT NULL COMMENT '注册资金(万元)',
  `registered_date` datetime NULL DEFAULT NULL COMMENT '公司注册成立日期',
  `province_id` bigint NULL DEFAULT NULL COMMENT '注册地区id(省)',
  `city_id` bigint NULL DEFAULT NULL COMMENT '注册地区ID(市)',
  `district_id` bigint NULL DEFAULT NULL COMMENT '注册地区ID(区)',
  `registered_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细注册地址',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商注册信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_supplier_industry
-- ----------------------------
DROP TABLE IF EXISTS `supplier_supplier_industry`;
CREATE TABLE `supplier_supplier_industry`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商ID',
  `industry_id` bigint NULL DEFAULT NULL COMMENT '主营行业ID',
  `industry_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主营行业名称',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_supplier_industry_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_supplier_industry_log`;
CREATE TABLE `supplier_supplier_industry_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商ID',
  `industry_id` bigint NULL DEFAULT NULL COMMENT '主营行业id',
  `industry_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主营行业名称',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_user_info
-- ----------------------------
DROP TABLE IF EXISTS `supplier_user_info`;
CREATE TABLE `supplier_user_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `account` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商联系人信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for supplier_user_info_log
-- ----------------------------
DROP TABLE IF EXISTS `supplier_user_info_log`;
CREATE TABLE `supplier_user_info_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '供应商基础信息主键ID',
  `account` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `create_user_id` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '供应商联系人信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
