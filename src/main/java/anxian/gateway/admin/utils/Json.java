package anxian.gateway.admin.utils;

import anxian.gateway.admin.module.common.domain.BaseObj;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Json工具类,可以拼接Json
 */
public class Json {


    private Map map = new LinkedHashMap();

    /**
     * 添加一个 JSON 属性，值为一个字符串，重复添加时产生数组<p/>
     * <p>
     * add("name", "value");<br/>
     * 添加一个字符串，产生的 JSON 如：{"name":"value"}<p/>
     * <p>
     * add("name", "value1");<br/>
     * add("name", "value2");<br/>
     * 添加两个同属性的字符串，产生的 JSON 如：{"name":["value1", "value2"]}<p/>
     *
     * @param key JSON 属性名
     */
    public void add(String key, String value) {
        addElement(key, value);
    }

    public void add(String key, int num) {
        addElement(key, new Integer(num));
    }

    public void add(String key, boolean b) {
        addElement(key, new Boolean(b));
    }

    /**
     * 添加一个 JSON 属性，值为一个 JSON，重复添加时产生 JSON 数组<p/>
     * <p>
     * Json json1 = new Json();<br/>
     * json1.add("name1", "value1");<br/>
     * json1.add("name2", "value2");<br/>
     * Json json = new Json();<br/>
     * json.add("message", json1);<br/>
     * 添加一个 JSON，产生的 JSON 如：{"message":{"name1":"value1", "name2":"value2"}}<p/>
     * <p>
     * Json json1 = new Json();<br/>
     * json1.add("name1", "value1");<br/>
     * json1.add("name2", "value2");<br/>
     * Json json2 = new Json();<br/>
     * json2.add("name1", "value3");<br/>
     * json2.add("name2", "value4");<br/>
     * Json json = new Json();<br/>
     * json.add("message", json1);<br/>
     * json.add("message", json2);<br/>
     * 添加两个同属性的 JSON，产生的 JSON 如：{"message":[{"name1":"value1", "name2":"value2"}, {"name1":"value3", "name2":"value4"}]}<p/>
     *
     * @param key  JSON 属性名
     * @param json JSON 格式的属性值
     */
    public void add(String key, Json json) {
        addElement(key, json);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int k = 0;
        for (Iterator i = map.keySet().iterator(); i.hasNext(); ) {
            String key = (String) (i.next());
            Object obj = map.get(key);
            if (k > 0) {
                sb.append(",");
            }
            appendKey(sb, key);
            if (obj instanceof String) {
                appendString(sb, (String) obj);
            } else if (obj instanceof List) {
                appendList(sb, (List) obj);
            } else if (obj instanceof Json) {
                appendJson(sb, (Json) obj);
            } else {
                appendOther(sb, obj);
            }
            k++;
        }
        sb.append("}");
        return sb.toString();
    }

    private void addElement(String key, Object obj) {
        if (!map.containsKey(key)) {
            if (obj instanceof Json) {
                List list = new ArrayList();
                list.add(obj);
                map.put(key, list);
            } else {
                map.put(key, obj);
            }
            return;
        }

        Object o = map.remove(key);

        if (o instanceof List) {
            ((List) o).add(obj);
            map.put(key, o);
            return;
        }

        // o is a String  
        List list = new ArrayList();
        list.add(o);
        list.add(obj);
        map.put(key, list);
    }

    /**
     * Append JSON property name
     *
     * @param sb
     * @param key
     */
    private void appendKey(StringBuilder sb, String key) {
        sb.append("\"").append(key).append("\":");
    }

    /**
     * Append JSON property value that is a String
     *
     * @param sb
     * @param str
     */
    private void appendString(StringBuilder sb, String str) {
        sb.append("\"").append(str).append("\"");
    }

    /**
     * Append JSON property value that is a Integer
     *
     * @param sb
     */
    private void appendOther(StringBuilder sb, Object obj) {
        sb.append(obj);
    }

    /**
     * Append JSON property value that is a List
     *
     * @param sb
     * @param list
     */
    private void appendList(StringBuilder sb, List list) {
        sb.append("[");
        for (int j = 0, m = list.size(); j < m; j++) {
            if (j > 0) {
                sb.append(",");
            }
            Object obj = list.get(j);
            if (obj instanceof String) {
                appendString(sb, (String) obj);
            } else if (obj instanceof Json) {
                appendJson(sb, (Json) obj);
            } else {
                appendOther(sb, obj);
            }
        }
        sb.append("]");
    }

    /**
     * Append JSON property value that is a JSON
     *
     * @param sb
     * @param json
     */
    private void appendJson(StringBuilder sb, Json json) {
        sb.append(json.toString());
    }

    public void getObjectValue(Object object, Json json) throws Exception {
        //我们项目的所有实体类都继承BaseEntity （所有实体基类：该类只是串行化一下）
        if (object != null && object instanceof BaseObj) {//if (object!=null )  ----begin
            // 拿到该类
            Class<?> clz = object.getClass();

            // 获取实体类的所有属性，返回Field数组
            Field[] fields = clz.getDeclaredFields();
            Field[] superFields = clz.getSuperclass().getSuperclass().getDeclaredFields();

            Field[] mergeFields = new Field[fields.length + superFields.length];

            System.arraycopy(fields, 0, mergeFields, 0, fields.length);
            System.arraycopy(superFields, 0, mergeFields, fields.length, superFields.length);


            for (Field field : mergeFields) {// --for() begin

                // 如果类型是String
                if (field.getGenericType().toString().equals(
                        "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    // 拿到该属性的gettet方法
                    /**
                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                     */
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));

                    String val = (String) m.invoke(object);// 调用getter方法获取属性值
                    if (val != null) {
                        json.add(field.getName(), val);
                    }

                }

                // 如果类型是Integer
                if (field.getGenericType().toString().equals(
                        "class java.lang.Integer")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), String.valueOf(val));
                    }

                }

                // 如果类型是Long
                if (field.getGenericType().toString().equals(
                        "class java.lang.Long")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Long val = (Long) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), String.valueOf(val));
                    }

                }

                // 如果类型是BigDecimal
                if (field.getGenericType().toString().equals(
                        "class java.math.BigDecimal")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));

                    BigDecimal val = (BigDecimal) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), String.valueOf(val));
                    }

                }

                // 如果属性名是ID，类型是T
                if (field.getName().equals("id") && field.getGenericType().toString().equals(
                        "T")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));

                    if (m.getReturnType().getName().equals("java.lang.Object")) {
                        BigDecimal val = (BigDecimal) m.invoke(object);
                        if (val != null) {
                            json.add(field.getName(), String.valueOf(val));
                        }
                    } else if (m.getReturnType().getName().equals("java.lang.Long")) {
                        Long val = (Long) m.invoke(object);
                        if (val != null) {
                            json.add(field.getName(), String.valueOf(val));
                        }
                    }


                }

                // 如果类型是Double
                if (field.getGenericType().toString().equals(
                        "class java.lang.Double")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), String.valueOf(val));
                    }

                }

                // 如果类型是Boolean 是封装类
                if (field.getGenericType().toString().equals(
                        "class java.lang.Boolean")) {
                    Method m = object.getClass().getMethod(
                            field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), val);
                    }

                }

                // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
                // 反射找不到getter的具体名
                if (field.getGenericType().toString().equals("boolean")) {
                    Method m = object.getClass().getMethod(
                            field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), val);
                    }

                }
                // 如果类型是Date
                if (field.getGenericType().toString().equals(
                        "class java.util.Date")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), new SimpleDateFormat("yyyy-MM-dd").format(val));
                    }

                }
                // 如果类型是Short
                if (field.getGenericType().toString().equals(
                        "class java.lang.Short")) {
                    Method m = object.getClass().getMethod(
                            "get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (val != null) {
                        json.add(field.getName(), val);
                    }

                }
                // 如果还需要其他的类型请自己做扩展

            }//for() --end

        }//if (object!=null )  ----end
    }

    // 把一个字符串的第一个字母大写、效率是最高的、
    private String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
