package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public boolean isExist(Object searchKey) {
        return searchKey != null; //
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public final void doSave(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, getSearchKey(r.getUuid()));
            size++;
        }
    }

    @Override
    public final void performDelete(String uuid) {
        fillDeletedElement(getSearchKey(uuid));
        storage[size - 1] = null;
        size--;
    }

    @Override
    public final void doUpdate(Resume r) {
        int index = getSearchKey(r.getUuid());
        storage[index] = r;
    }

    @Override
    public final Resume doGet(String uuid) {
        return storage[getSearchKey(uuid)];
    }

    @Override
    public abstract Integer getSearchKey(String uuid);

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

}
