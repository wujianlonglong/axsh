package anxian.gateway.admin.utils;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoqichao on 16-4-12.
 */
public class ExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 生成excel对象
     *
     * @param templateFileName 模板文件名称
     * @param list             数据列表
     * @param resultFileName   导出excel文件名称
     */
    public String createExcel(String templateFileName, List<?> list, String resultFileName) {
        // 创建XLSTransformer对象
        XLSTransformer xlsTransformer = new XLSTransformer();

        // 获取java编译后的跟路径
        URL url = this.getClass().getClassLoader().getResource("");

        String srcFilePath = url.getPath() + templateFileName;

        String destFilePath = url.getPath() + resultFileName;
        Map<String, Object> beansParams = new HashMap<>();
        beansParams.put("list", list);

        try {
            File exportFile = new File(destFilePath);
            if (!exportFile.getParentFile().exists()) {
                exportFile.getParentFile().mkdirs();
            }

            // 判断文件是否存在
//			if (exportFile.exists()) {
//				exportFile.delete();
//			}

            xlsTransformer.transformXLS(srcFilePath, beansParams, destFilePath);
        } catch (InvalidFormatException e) {
            log.error("生成Excel数据格式异常", e);
        } catch (IOException e) {
            log.error("IO文件异常", e);
        }

        return destFilePath;
    }

    /**
     * 获取excel单元格内容
     *
     * @param cell 单元格
     * @return 单元格的值
     */
    public static String getCellValue(Cell cell) {
        Assert.notNull(cell);
        int cellType = cell.getCellType();
        if (XSSFCell.CELL_TYPE_BOOLEAN == cellType) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (XSSFCell.CELL_TYPE_NUMERIC == cellType) {
            Double value = cell.getNumericCellValue();
            long longValue = value.longValue();
            if (longValue == value) {
                return String.valueOf(longValue);
            } else {
                return String.valueOf(value);
            }
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }
}
