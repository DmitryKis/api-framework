package pojoModels;


public class UserPojo {
    private Data data;
    private Support support;

    public Data getData() {
        return data;
    }

    public Support getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return "UserPojo{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}

