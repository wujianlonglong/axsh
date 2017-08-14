package anxian.gateway.admin.utils.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Created by Jianghe on 16/1/25.
 */
public class LocalDateEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!org.springframework.util.StringUtils.isEmpty(text)) {
            String formatterStr = null;
            if (text.indexOf("/") != -1) {
                formatterStr = "MM/dd/yyyy";
            } else if (text.indexOf("-") != -1) {
                formatterStr = "yyyy-MM-dd";
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatterStr);
            TemporalAccessor temporalAccessor = dateTimeFormatter.parse(text);
            setValue(LocalDate.from(temporalAccessor));
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
