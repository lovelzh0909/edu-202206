package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;


import java.io.Serializable;
import java.time.LocalDateTime;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-17
 */
@Data
@Accessors(chain = true)
@TableName("paper")
@ApiModel(value = "Papers对象", description = "")
public class Papers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @TableField("creater_phone")
    private String Phone;

    @TableField("paper_context")
    private String papercontext;

    @ApiModelProperty("试卷使用状态（待发布，已发布，已完成，答题中，待批阅，批阅完成）")
    @TableField("paper_status")
    private String paperstatus;

    @ApiModelProperty("使用次数	")
    @TableField("paper_num")
    private Integer papernum;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;

    @ApiModelProperty("试卷名")
    @TableField("source")
    private String source;

    @TableField("total_Score")
    private double totalScore;

    @TableField("total_Time")
    private String totalTime;

//    @TableField("create_time")
//    private String createTime;
    
    @TableField("description")
    private String description;

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
