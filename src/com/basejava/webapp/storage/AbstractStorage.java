package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public final void save(Resume r) {
        if (checkIfExists(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        performSave(r);
    }

    public final void delete(String uuid) {
        if (!checkIfExists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        performDelete(uuid);
    }

    public final void update(Resume r) {
        if (!checkIfExists(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        }
        performUpdate(r);
    }

    public final Resume get(String uuid) {
        if (!checkIfExists(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return performGet(uuid);
    }

    public abstract boolean checkIfExists(String uuid);

    public abstract void performSave(Resume r);

    public abstract void performDelete(String uuid);

    public abstract void performUpdate(Resume r);

    public abstract Resume performGet(String uuid);
}
