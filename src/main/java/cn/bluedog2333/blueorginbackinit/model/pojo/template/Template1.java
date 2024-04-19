package cn.bluedog2333.blueorginbackinit.model.pojo.template;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName template1
 */
@TableName(value ="template1")
@Data
public class Template1 implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 传入5个长度的数组 json
     */
    private String title;

    /**
     * 
     */
    private String avatar;

    /**
     * HashMap,json
     */
    private String info;

    /**
     * 
     */
    private String label;

    /**
     * List<HashMap>
     */
    private String nav;

    /**
     * 博客链接
     */
    private String href;

    /**
     * HashMap,json
     */
    private String say;

    /**
     * List<HashMap>
     */
    private String works;

    /**
     * List<HashMap>
     */
    private String skills;

    /**
     * List<HashMap>
     */
    private String photos;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Date createTime;

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
        Template1 other = (Template1) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()))
            && (this.getNav() == null ? other.getNav() == null : this.getNav().equals(other.getNav()))
            && (this.getHref() == null ? other.getHref() == null : this.getHref().equals(other.getHref()))
            && (this.getSay() == null ? other.getSay() == null : this.getSay().equals(other.getSay()))
            && (this.getWorks() == null ? other.getWorks() == null : this.getWorks().equals(other.getWorks()))
            && (this.getSkills() == null ? other.getSkills() == null : this.getSkills().equals(other.getSkills()))
            && (this.getPhotos() == null ? other.getPhotos() == null : this.getPhotos().equals(other.getPhotos()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        result = prime * result + ((getNav() == null) ? 0 : getNav().hashCode());
        result = prime * result + ((getHref() == null) ? 0 : getHref().hashCode());
        result = prime * result + ((getSay() == null) ? 0 : getSay().hashCode());
        result = prime * result + ((getWorks() == null) ? 0 : getWorks().hashCode());
        result = prime * result + ((getSkills() == null) ? 0 : getSkills().hashCode());
        result = prime * result + ((getPhotos() == null) ? 0 : getPhotos().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", title=").append(title);
        sb.append(", avatar=").append(avatar);
        sb.append(", info=").append(info);
        sb.append(", label=").append(label);
        sb.append(", nav=").append(nav);
        sb.append(", href=").append(href);
        sb.append(", say=").append(say);
        sb.append(", works=").append(works);
        sb.append(", skills=").append(skills);
        sb.append(", photos=").append(photos);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}