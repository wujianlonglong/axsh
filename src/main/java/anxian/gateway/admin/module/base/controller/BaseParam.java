package anxian.gateway.admin.module.base.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseParam implements Serializable {


    private int size = 10;


    private int page = 1;
}
