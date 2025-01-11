package hohich.serializer;

import hohich.model.Note;

import java.io.File;
import java.io.IOException;

public interface Serializer<T> {
    void serialize(T obj) throws IOException;
}
