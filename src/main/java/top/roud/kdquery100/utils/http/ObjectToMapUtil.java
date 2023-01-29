package top.roud.kdquery100.utils.http;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/4
 * @version:
 */
public class ObjectToMapUtil {
    public ObjectToMapUtil() {
    }

    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        } else {
            Map<String, String> map = new HashMap();
            List<Field> allField = getAllField(obj);

            String fieldName;
            String fieldValue;
            for(Iterator var3 = allField.iterator(); var3.hasNext(); map.put(fieldName, fieldValue)) {
                Field field = (Field)var3.next();
                field.setAccessible(true);
                fieldName = field.getName();
                fieldValue = "";
                if (field.getType() == String.class || field.getType() == Integer.class || field.getType() == Integer.TYPE) {
                    fieldValue = field.get(obj) == null ? "" : field.get(obj).toString();
                }
            }

            return map;
        }
    }

    private static List<Field> getAllField(Object obj) {
        List<Field> fieldList = new ArrayList();

        for(Class clazz = obj.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }

        return fieldList;
    }
}

