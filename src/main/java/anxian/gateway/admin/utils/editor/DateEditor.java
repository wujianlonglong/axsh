package anxian.gateway.admin.utils.editor;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;

/**
 * Created by Jianghe on 16/2/2.
 */
public class DateEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.isEmpty(text)) {
            String formatterStr = null;
            if (text.indexOf("/") != -1) {
                formatterStr = "MM/dd/yyyy";
            } else if (text.indexOf("-") != -1) {
                formatterStr = "yyyy-MM-dd";
            }
            setValue(new CustomDateEditor(new SimpleDateFormat(formatterStr), true));
        }

    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
