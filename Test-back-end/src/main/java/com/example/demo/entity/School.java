package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

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
 * @author 作者
 * @since 2022-04-12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("school")
@ApiModel(value = "School对象", description = "")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学校编号")
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("学校名")
    @TableField("school")
    private String school;

    @ApiModelProperty("学生人数")
    @TableField("student_num")
    private Integer studentnum;

    @ApiModelProperty("教师人数")
    @TableField("teacher_num")
    private Integer teachernum;

    @ApiModelProperty("电话号码")
    @TableField("telephone")
    private String telephone;

    @Version
    private int version;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;
}
