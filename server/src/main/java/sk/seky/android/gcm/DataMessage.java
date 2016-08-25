package sk.seky.android.gcm;

import java.io.Serializable;

/**
 * Created by lsekerak on 3. 4. 2016.
 */
public class DataMessage extends Message {

    public DataMessage(Class type, Object payload) {
        PayloadDesc desc = new PayloadDesc();
        desc.type = type.getName();
        desc.data = payload;
        setData(desc);
    }

    public static class PayloadDesc implements Serializable {
        private String type;
        private Object data;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("PayloadDesc{");
            sb.append("type='").append(type).append('\'');
            sb.append(", data=").append(data);
            sb.append('}');
            return sb.toString();
        }
    }
}
