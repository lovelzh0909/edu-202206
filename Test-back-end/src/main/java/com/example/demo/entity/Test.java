package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@TableName("test")
@ApiModel(value = "Test对象", description = "")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("考试编号")
    @TableId("test_id")
    private Integer testId;

    @ApiModelProperty("试卷编号")
    @TableField("paper_id")
    private Integer paperId;

    @ApiModelProperty("考场号")
    @TableField("room_id")
    private Integer roomId;

    @ApiModelProperty("课程名称")
    @TableField("course_name")
    private String courseName;

    @ApiModelProperty("考试老师")
    @TableField("test_teacher")
    private String testTeacher;

    @ApiModelProperty("教师电话")
    @TableField("teacher_phone")
    private String teacherPhone;

    @ApiModelProperty("监考老师")
    @TableField("invigilator")
    private String invigilator;

    @ApiModelProperty("监考老师电话")
    @TableField("invigilator_id")
    private String invigilatorId;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;

    @ApiModelProperty("创建日期")
    @TableField("create_date")
    private String createDate;

    @ApiModelProperty("考试时间")
    @TableField("test_time")
    private String testTime;

    @ApiModelProperty("持续时间")
    @TableField("time_last")
    private Integer timeLast;

    @ApiModelProperty("考试类型")
    @TableField("test_type")
    private String testType;

    @ApiModelProperty("考试状态")
    @TableField("test_status")
    private Integer testStatus;

    @ApiModelProperty("批阅状态")
    @TableField("pystatus")
    private String pystatus;

    @ApiModelProperty("考试设置状态")
    @TableField("test_setting_id")
    private Integer testSettingId;

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

    private List<String> studentPhone;
}
