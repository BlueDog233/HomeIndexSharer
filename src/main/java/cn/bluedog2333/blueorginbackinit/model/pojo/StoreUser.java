package cn.bluedog2333.blueorginbackinit.model.pojo;

import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
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
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String avatar;
    private String username;
    private Integer isPublish;
    private String style;
    private String textData;
    private List<Info> photoData;
    private Integer template;
    private String email;
    private List<Integer> stars;
    @TableField(exist = false)
    private LocalDateTime updateTime;
    @TableField(exist = false)
    private LocalDateTime createTime;
    public String getHtml(){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"user/"+username+".html"));
        return FileUtil.readString(newFile, Charset.defaultCharset());
    }
    public String getJson(){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"user/"+username+".json"));
        return FileUtil.readString(newFile, Charset.defaultCharset());
    }
    public void setHtml(String s){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"user/"+username+".html"));
        FileUtil.writeUtf8String(s,newFile);
    }
    public void setJson(String s){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"user/"+username+".json"));
        FileUtil.writeUtf8String(s,newFile);
    }

}