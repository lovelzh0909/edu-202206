package com.example.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用于存储学生问题答案	
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("paper_justify")
@ApiModel(value = "PaperJustify对象", description = "用于存储学生问题答案	")
public class PaperJustify implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(format="id")
    private Integer id;

    @TableField("question_id")
    @JSONField(format="question_id")
    private Integer questionId;

    @JSONField(format="student_phone")
    @TableField("student_phone")
    private String studentphone;

    @JSONField(format="exmaineAnswer")
    @TableField("student_answer")
    private String exmaineAnswer;

    @JSONField(format="correctAnswer")
    @TableField("correct_answer")
    private String correctAnswer;

    @JSONField(format="score")
    @ApiModelProperty("1 dui 0 cuo")
    @TableField("justify")
    private Double score;

    @JSONField(format="totalscore")
    @TableField("score")
    private Double totalscore;

    @JSONField(format="testId")
    @TableField("test_id")
    private Integer testId;

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
