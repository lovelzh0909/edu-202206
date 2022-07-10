package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 用于存储学生问题答案	
 * </p>
 *
 * @author ct
 * @since 2022-07-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("paper_justify")
@ApiModel(value = "PaperJustify对象", description = "用于存储学生问题答案	")
public class PaperJustify implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("改卷编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("考试编号")
    @TableField("test_id")
    private Integer testId;

    @ApiModelProperty("题目编号")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty("学生电话")
    @TableField("student_phone")
    private String studentPhone;

    @ApiModelProperty("学生答案")
    @TableField("student_answer")
    private String studentAnswer;

    @ApiModelProperty("正确答案")
    @TableField("correct_answer")
    private String correctAnswer;

    @ApiModelProperty("教师批阅分数")
    @TableField("justify")
    private Double justify;

    @ApiModelProperty("题目实际分数")
    @TableField("score")
    private Double score;

    @TableField("version")
    @Version
    private Integer version;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_delete")
    private Boolean isDelete;

    private String exmaineAnswer;
}
