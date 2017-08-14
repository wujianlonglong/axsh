package client.api.item.model;

import client.api.item.domain.PackageModel;
import client.api.item.domain.Product;
import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 16-2-18.
 */
@Data
public class ProductPackage extends Product {

    private List<PackageModel> packageModels;

}
