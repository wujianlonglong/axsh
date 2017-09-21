package client.api.sale.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by kimiyu on 16/12/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErpSaleDTO {

    private String saleName;

    private String saleType;

    private Integer status=null;

    private Integer syncStatus = null;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer page;

    private Integer size;
}
