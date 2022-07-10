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
@TableName("student_test_flag")
@ApiModel(value = "StudentTestFlag对象", description = "")
public class StudentTestFlag implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标记学生编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("学生编号")
    @TableField("student_id")
    private String studentId;

    @ApiModelProperty("考试编号")
    @TableField("test_id")
    private Integer testId;

    @ApiModelProperty("标记")
    @TableField("flag")
    private Integer flag;

    @ApiModelProperty("切屏次数")
    @TableField("cut_screen")
    private Integer cutScreen;

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
