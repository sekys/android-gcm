package sk.seky.android.gcm;

import java.io.Serializable;

/**
 * Created by lsekerak on 3. 4. 2016.
 */
public class Message implements Serializable {
    private String to;
    private Object data;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("to='").append(to).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
