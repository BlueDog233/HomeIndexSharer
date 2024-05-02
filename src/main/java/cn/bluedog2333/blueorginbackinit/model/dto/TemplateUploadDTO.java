package cn.bluedog2333.blueorginbackinit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateUploadDTO {
    private boolean isPrivate;
    private String describe;
    private String name;

}
