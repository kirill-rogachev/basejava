package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (checkIfResumePresent(r.getUuid())) {
            return;
        }
        if (size < storage.length) {
            storage[size++] = r;
        } else {
            System.out.println("Ошибка: невозможно добавить резюме, хранилище заполнено");
        }
    }

    public void update(Resume resume) {
        if (checkIfResumeAbsent(resume.getUuid())) {
            return;
        }
        System.out.println("Резюме " + resume.getUuid() + " обновлено");
    }

    public Resume get(String uuid) {
        if (checkIfResumeAbsent(uuid)) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (checkIfResumeAbsent(uuid)) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            }
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

    private boolean checkIfResumeAbsent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return false;
            }
        }
        System.out.println("Ошибка: резюме с uuid " + uuid + " не найдено");
        return true;
    }

    private boolean checkIfResumePresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.out.println("Ошибка: резюме с uuid " + uuid + " уже есть в хранилище");
                return true;
            }
        }
        return false;
    }
}
