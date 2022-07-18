package com.example.demo.QuestionManage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Data
@Accessors(chain = true)
@TableName("question")
@ApiModel(value = "Question对象", description = "")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("quesType_id")
    private int quesTypeId;

    @TableField("course_name")
    private String coursename;

    @TableField("stem")
    private String stem;

    @TableField("choice_a")
    private String choiceA;

    @TableField("choice_b")
    private String choiceB;

    @TableField("choice_c")
    private String choiceC;

    @TableField("choice_d")
    private String choiceD;

    @TableField("choice_e")
    private String choiceE;

    @TableField("choice_f")
    private String choiceF;

    @TableField("choice_g")
    private String choiceG;

    @TableField("answer")
    private String answer;

    @TableField("user_id")
    private String userId;

//    @TableField("create_time")
//    private LocalDateTime createTime;

    @TableField("user_name")
    private String userName;

    @TableField("knowledge_name")
    private String knowledgeName;

    @TableField("score")
    private Double score;

    @TableField("difficulty")
    private Double difficulty;

    @TableField("point_id")
    private String pointId;

    @TableField("content")
    private String content;

    @TableField(exist = false)
    private  String studentAnswer;

    @TableField(exist = false)
    private  Double getScore;

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
