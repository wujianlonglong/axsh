package anxian.gateway.admin.utils.editor;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class IntegerEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!text.equals("请选择")) {
            if (text == null || text.equals("")) {
                text = "0";
            } else if (text.indexOf(",") != -1) {
                text = text.split(",")[0];
            }
            setValue(Integer.parseInt(text));
        }

    }

    @Override
    public String getAsText() {
        return getValue().toString();
    }
}
