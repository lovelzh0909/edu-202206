package com.example.demo.QuestionManage.entity;

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
 * @since 2022-04-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("question_rel_score")
@ApiModel(value = "Questionrelscore对象", description = "")
public class Questionrelscore implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("paper_id")
    private Integer paperId;

    @TableField("question_id")
    private Integer questionId;

    @TableField("score")
    private Double score;

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
