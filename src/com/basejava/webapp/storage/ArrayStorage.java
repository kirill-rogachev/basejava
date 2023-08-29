package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage{

    public static final int STORAGE_LIMIT = 10000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            printErrorResumeAbsent(r.getUuid());
        } else {
            System.out.println("Резюме " + r.getUuid() + " обновлено");
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Ошибка: резюме с uuid " + r.getUuid() + " уже есть в хранилище");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: невозможно добавить резюме, хранилище заполнено");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printErrorResumeAbsent(uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printErrorResumeAbsent(uuid);
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    private void printErrorResumeAbsent(String uuid) {
        System.out.println("Ошибка: резюме с uuid " + uuid + " не найдено");
    }
}
