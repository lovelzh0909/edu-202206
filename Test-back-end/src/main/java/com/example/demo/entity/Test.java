package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-12
 */
@Getter
@ToString
@Setter
@Accessors(chain = true)
@TableName("test")
@ApiModel(value = "Test对象", description = "")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "test_id", type = IdType.AUTO)
    private Integer testId;

    @TableField("test_setting_id")
    private Integer testsettingid;

    @TableField("room_id")
    private Integer roomId;

    @TableField("note")
    private String note;

    @TableField("test_status")/*未开始 考试中 待批阅 考试完成 */
    private int teststatus;

    @TableField("invigilator")
    private String invigilator;

    @TableField("test_teacher")
    private String testteacher;

    @TableField("course_name")
    private String coursename;


    @TableField("create_date")
    private String createdate;

    @TableField("test_time")
    private String testtime;

    @TableField("time_last")
    private Integer timelast;

    @TableField("pystatus")
    private String pystatus;

    @TableField("teacher_phone")
    private String teacherphone;

    @TableField("paper_id")
    private int paperId;

    @TableField("test_type")
    private String testtype;

    @TableField("invigilator_id")
    private String invigilatorId;

    @TableField(exist = false)
    private List<String> studentphone;

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
