package com.klcw.app.lib.recyclerview.floormanager;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class SerializableList<E> extends ArrayList<E> implements Externalizable {
    public SerializableList() {
        super();
    }

    public SerializableList(Collection<? extends E> c) {
        super(c);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int elementCount = in.readInt();
        this.ensureCapacity(elementCount);
        for (int i = 0; i < elementCount; i++) {
            this.add((E) in.readObject());
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(size());
        for (int i = 0; i < size(); i++) {
            if (get(i) instanceof Serializable) {
                out.writeObject(get(i));
            } else {
                out.writeObject(null);
            }
        }
    }
}