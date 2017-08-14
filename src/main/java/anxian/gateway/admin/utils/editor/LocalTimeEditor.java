package anxian.gateway.admin.utils.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Created by Jianghe on 16/2/3.
 */
public class LocalTimeEditor extends PropertiesEditor {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!org.springframework.util.StringUtils.isEmpty(text)) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            TemporalAccessor temporalAccessor = dateTimeFormatter.parse(text);
            setValue(LocalTime.from(temporalAccessor));
        }

    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
