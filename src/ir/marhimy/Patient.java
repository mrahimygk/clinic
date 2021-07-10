package ir.marhimy;

import java.util.UUID;

public class Patient {
    public final UUID id;

    public Patient() {
        this.id = UUID.randomUUID();
    }
}
