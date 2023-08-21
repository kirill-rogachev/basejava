package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int STORAGE_LIMIT = 10000;
    private Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            System.out.println("Резюме " + resume.getUuid() + " обновлено");
        } else {
            printErrorResumeAbsent(resume.getUuid());
        }
    }

    public void save(Resume r) {
        if (findIndex(r.getUuid()) >= 0) {
            System.out.println("Ошибка: резюме с uuid " + r.getUuid() + " уже есть в хранилище");
            return;
        }
        if (size < STORAGE_LIMIT) {
            storage[size++] = r;
        } else {
            System.out.println("Ошибка: невозможно добавить резюме, хранилище заполнено");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        printErrorResumeAbsent(uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            printErrorResumeAbsent(uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private void printErrorResumeAbsent(String uuid) {
        System.out.println("Ошибка: резюме с uuid " + uuid + " не найдено");
    }
}
