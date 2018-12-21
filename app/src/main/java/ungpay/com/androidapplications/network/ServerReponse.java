package ungpay.com.androidapplications.network;

import android.os.Parcel;
import android.os.Parcelable;

public class ServerReponse<T> implements Parcelable {

    public String reason;
    public T result;
    public int error_code;

    public ServerReponse() {

    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    protected ServerReponse(Parcel in) {
        reason = in.readString();
        error_code = in.readInt();
    }

    public static final Creator<ServerReponse> CREATOR = new Creator<ServerReponse>() {
        @Override
        public ServerReponse createFromParcel(Parcel in) {
            return new ServerReponse(in);
        }

        @Override
        public ServerReponse[] newArray(int size) {
            return new ServerReponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reason);
        dest.writeInt(error_code);
    }
}