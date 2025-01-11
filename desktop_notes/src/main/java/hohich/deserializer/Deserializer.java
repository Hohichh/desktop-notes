package hohich.deserializer;

import hohich.model.Note;

public interface Deserializer<T> {
    T deserialize(String resource);
}
