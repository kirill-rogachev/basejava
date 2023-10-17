package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public void save(Resume r) {
        if (checkIfExists(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        add(r);
    }

    public final void delete(String uuid) {
        if (!checkIfExists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        remove(uuid);
    }

    public final void update(Resume r) {
        if (!checkIfExists(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        }
        change(r);
    }

    public final Resume get(String uuid) {
        if (!checkIfExists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return receive(uuid);
    }

    public abstract boolean checkIfExists(String uuid);

    public abstract void add(Resume r);

    public abstract void remove(String uuid);

    public abstract void change(Resume r);

    public abstract Resume receive(String uuid);
}
