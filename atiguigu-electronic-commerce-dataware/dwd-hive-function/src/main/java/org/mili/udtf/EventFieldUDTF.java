package org.mili.udtf;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mamenglong
 * @date 2022/10/20
 */
public class EventFieldUDTF extends GenericUDTF {
    /**
     * 该方法用于定义输出的参数名及其类型
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> fieldNames = new ArrayList<>();
        List<ObjectInspector> fieldOIs = new ArrayList<>();
        fieldNames.add("event_name");
        fieldNames.add("event_json");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    @Override
    public void process(Object[] objects) throws HiveException {
        String line = objects[0].toString();

        if (StringUtils.isBlank(line)) {
            return;
        }
        String[] contents = line.split("\\|");
        try {
            JSONObject jsonObject = new JSONObject(contents[1]);
            JSONArray et = jsonObject.getJSONArray("et");
            if (null == et) {
                return;
            }
            for (int i = 0; i < et.length(); i++) {
                String[] res = new String[2];
                JSONObject event = et.getJSONObject(i);
                res[0] = event.getString("en");
                res[1] = event.toString();
                forward(res);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws HiveException {

    }
}
