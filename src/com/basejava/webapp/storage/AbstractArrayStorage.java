package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public boolean checkIfExists(String uuid) {
        int index = getIndex(uuid);
        return index >= 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final void add(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertElement(r, getIndex(r.getUuid()));
            size++;
        }
    }

    public final void remove(String uuid) {
        fillDeletedElement(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    public final void change(Resume r) {
        int index = getIndex(r.getUuid());
        storage[index] = r;
    }

    public final Resume receive(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}
