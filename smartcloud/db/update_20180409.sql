DROP TABLE IF EXISTS tb_project_pano;
CREATE TABLE IF NOT EXISTS tb_project_pano(
pano_id VARCHAR(100) PRIMARY KEY COMMENT '主键',
project_id VARCHAR(100) COMMENT '项目id',
resource_id VARCHAR(100) COMMENT '文件id'
)ENGINE = INNODB CHARSET = utf8 COMMENT '项目全景图表';