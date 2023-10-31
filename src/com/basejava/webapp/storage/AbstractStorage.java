package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    public final void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(r.getUuid())) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(r);
    }

    public final void delete(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        performDelete(uuid);
    }

    public final void update(Resume r) {
        if (!isExist(r.getUuid())) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(r);
    }

    public final Resume get(String uuid) {
        if (!isExist(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(uuid);
    }

    public abstract boolean isExist(Object searchKey);

    public abstract void doSave(Resume r);

    public abstract void performDelete(String uuid);

    public abstract void doUpdate(Resume r);

    public abstract Resume doGet(String uuid);

    public abstract Object getSearchKey(String uuid);

    private Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        isExist(searchKey);
        return null;
    }
}
