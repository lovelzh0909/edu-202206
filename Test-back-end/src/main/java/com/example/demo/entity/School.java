package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ct
 * @since 2022-07-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("school")
@ApiModel(value = "School对象", description = "")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学校编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("学校名")
    @TableField("school")
    private String school;

    @ApiModelProperty("学生人数")
    @TableField("student_num")
    private Integer studentNum;

    @ApiModelProperty("教师人数")
    @TableField("teacher_num")
    private Integer teacherNum;

    @ApiModelProperty("电话号码")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty("传真")
    @TableField("fax")
    private String fax;

    @TableField("version")
    @Version
    private Integer version;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;
}
