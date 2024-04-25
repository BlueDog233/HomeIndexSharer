package cn.bluedog2333.blueorginbackinit.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName storeuser
 */
@TableName(value ="storeuser")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreUser {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private Integer isPublish;

    /**
     * 
     */
    private String html;

    /**
     * 
     */
    private String json;

    /**
     * 
     */
    private String style;

    /**
     * 
     */
    private String textData;

    /**
     * 
     */
    private List<Info> photoData;

    /**
     * 
     */
    private Integer template;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String stars;
    @TableField(exist = false)
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private LocalDateTime createTime;



}