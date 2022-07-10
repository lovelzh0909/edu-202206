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
@TableName("question")
@ApiModel(value = "Question对象", description = "")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("试题编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("试题类型（1单选 2多选 3填空 4简答 5判断）")
    @TableField("quesType_id")
    private Integer quesTypeId;

    @ApiModelProperty("课程名称")
    @TableField("course_name")
    private String courseName;

    @ApiModelProperty("题目内容")
    @TableField("stem")
    private String stem;

    @ApiModelProperty("A选项")
    @TableField("choice_a")
    private String choiceA;

    @ApiModelProperty("B选项")
    @TableField("choice_b")
    private String choiceB;

    @ApiModelProperty("C选项")
    @TableField("choice_c")
    private String choiceC;

    @ApiModelProperty("D选项")
    @TableField("choice_d")
    private String choiceD;

    @ApiModelProperty("E选项")
    @TableField("choice_e")
    private String choiceE;

    @ApiModelProperty("F选项")
    @TableField("choice_f")
    private String choiceF;

    @ApiModelProperty("G选项")
    @TableField("choice_g")
    private String choiceG;

    @ApiModelProperty("答案")
    @TableField("answer")
    private String answer;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("创建者")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("知识点名称")
    @TableField("knowledge_name")
    private String knowledgeName;

    @ApiModelProperty("分数")
    @TableField("score")
    private Double score;

    @ApiModelProperty("难度")
    @TableField("difficulty")
    private Double difficulty;

    @ApiModelProperty("知识点ID")
    @TableField("point_id")
    private String pointId;

    @ApiModelProperty("用户编号")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("选项集合")
    @TableField("content")
    private String content;

    @TableField("version")
    @Version
    private Integer version;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    private Double getScore;

    private String studentAnswer;

}
