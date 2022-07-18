package com.example.demo.ScoreManage.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Score {
    @TableField("exam_code")
    private Integer examCode;
    @TableField("student_id")
    private String studentId;
    @TableField("subject")
    private String subject;
    @TableField("pt_Score")
    private Double ptScore;
    @TableField("et_Score")
    private Double etScore;
    @TableField("score")
    private Double score;
    @TableField("score_id")
    private Integer scoreId;
    @TableField("answer_date")
    private String answerDate;
    @TableField("teacher_phone")
    private String teacherPhone;
    @TableField(exist = false)
    private int num;
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