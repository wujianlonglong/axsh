package anxian.gateway.admin.module.base.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseParam implements Serializable {

    private int page = 1;

    private int size = 20;
}
