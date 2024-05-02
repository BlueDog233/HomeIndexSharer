package cn.bluedog2333.blueorginbackinit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AiRequestDTO {
    private Object data;
    private String message;
}
