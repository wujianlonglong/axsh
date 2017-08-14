package anxian.gateway.admin.utils.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Created by jiangzhe on 15-11-16.
 */
public class LocalDateTimeEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!text.equals("")) {
            String formatterStr = null;

            if (text.indexOf("T") != -1) {
                text = text.split("T")[0];
            }

            if (text.length() == 10) {
                text += " 00:00:00";
            }

            if (text.indexOf("/") != -1) {
                formatterStr = "MM/dd/yyyy HH:mm:ss";
            } else if (text.indexOf("-") != -1) {
                formatterStr = "yyyy-MM-dd HH:mm:ss";
            }

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatterStr);
            TemporalAccessor temporalAccessor = dateTimeFormatter.parse(text);
            setValue(LocalDateTime.from(temporalAccessor));
        }
    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }

}
