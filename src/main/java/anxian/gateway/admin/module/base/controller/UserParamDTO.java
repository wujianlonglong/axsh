package anxian.gateway.admin.module.base.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserParamDTO extends BaseParam implements Serializable {

    private String searchContent;
}
