package client.api.item.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchCoditionModel<T> implements Serializable {
    private int page;
    private int size;

    private T searchCodition;

}
