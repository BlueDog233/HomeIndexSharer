package cn.bluedog2333.blueorginbackinit.model.pojo;

import cn.bluedog2333.blueorginbackinit.properties.FileStoreProperties;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName customindex
 */
@TableName(value ="customindex")
@Data
public class Template implements Serializable {
    @TableId
    private Integer id;
    private Date updateTime;
    private Date createTime;
    private String author;
    private List<Info> photo;
    private String describe;
    private Integer starcount;
    private String name;
    public String getHtml(){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"template/"+ name+"/index.html"));
        return FileUtil.readString(newFile, Charset.defaultCharset());
    }
    public String getJson(){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"template/"+ name+"/index.json"));
        return FileUtil.readString(newFile, Charset.defaultCharset());
    }
    public void setHtml(String s){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"template/"+ name+"/index.html"));
        FileUtil.writeUtf8String(s,newFile);
    }
    public void setJson(String s){
        File newFile = FileUtil.touch(new File(FileStoreProperties.storePath+"template/"+ name+"/index.json"));
        FileUtil.writeUtf8String(s,newFile);
    }
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Template other = (Template) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getDescribe() == null ? other.getDescribe() == null : this.getDescribe().equals(other.getDescribe()))
            && (this.getStarcount() == null ? other.getStarcount() == null : this.getStarcount().equals(other.getStarcount()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getDescribe() == null) ? 0 : getDescribe().hashCode());
        result = prime * result + ((getStarcount() == null) ? 0 : getStarcount().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", author=").append(author);
        sb.append(", photo=").append(photo);
        sb.append(", describe=").append(describe);
        sb.append(", starcount=").append(starcount);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}