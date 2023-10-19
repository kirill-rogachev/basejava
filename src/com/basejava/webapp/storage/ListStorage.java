package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    public boolean checkIfExists(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public void performSave(Resume r) {
        storage.add(r);
    }

    @Override
    public void performDelete(String uuid) {
        Iterator<Resume> iterator = storage.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume.getUuid().equals(uuid)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void performUpdate(Resume r) {
        ListIterator<Resume> iterator = storage.listIterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            if (resume.getUuid().equals(r.getUuid())) {
                iterator.set(r);
            }
        }
    }

    @Override
    public Resume performGet(String uuid) {
        for (Resume resume : storage) {
            if (resume.getUuid().equals(uuid)) {
                return resume;
            }
        }
        return null;
    }
}
