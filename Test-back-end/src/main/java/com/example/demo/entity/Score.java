package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 成绩管理表
 * </p>
 *
 * @author ct
 * @since 2022-07-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("score")
@ApiModel(value = "Score对象", description = "成绩管理表")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分数编号")
    @TableId("score_id")
    private Integer scoreId;

    @ApiModelProperty("考试编号")
    @TableField("exam_code")
    private Integer examCode;

    @ApiModelProperty("学号")
    @TableField("student_id")
    private String studentId;

    @ApiModelProperty("教师电话")
    @TableField("teacher_phone")
    private String teacherPhone;

    @ApiModelProperty("课程名称")
    @TableField("subject")
    private String subject;

    @ApiModelProperty("平时成绩")
    @TableField("pt-score")
    private Double ptscore;

    @ApiModelProperty("期末成绩")
    @TableField("et_score")
    private Double etScore;

    @ApiModelProperty("总成绩")
    @TableField("score")
    private Double score;

    @ApiModelProperty("答题日期//成绩登记时间")
    @TableField("answer_date")
    private String answerDate;

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
