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

    @ApiModelProperty("试卷编号")
    @TableId(value = "paper_id", type = IdType.AUTO)
    private Integer paperId;

    @ApiModelProperty("创建者电话")
    @TableField("creater_phone")
    private String createrphone;

    @ApiModelProperty("试卷包含的题目")
    @TableField("paper_context")
    private String papercontext;

    @ApiModelProperty("试卷使用状态（待发布，已发布，已完成，答题中，待批阅，批阅完成）")
    @TableField("paper_status")
    private String paperstatus;

    @ApiModelProperty("使用次数\r\n")
    @TableField("paper_num")
    private Integer papernum;

    @ApiModelProperty("试卷名")
    @TableField("source")
    private String source;

    @ApiModelProperty("试卷描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("试卷时长")
    @TableField("total_time")
    private String totaltime;

    @ApiModelProperty("总分")
    @TableField("total_score")
    private Double totalscore;

    @ApiModelProperty("备注")
    @TableField("note")
    private String note;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @TableField("version")
    @Version
    private Integer version;

}
