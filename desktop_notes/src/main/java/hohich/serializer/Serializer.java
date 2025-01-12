package hohich.serializer;

public interface Serializer<T> {
    void serialize(T obj, String filePath);
}
