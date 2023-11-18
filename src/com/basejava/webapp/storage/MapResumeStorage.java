package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getListToSort() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
