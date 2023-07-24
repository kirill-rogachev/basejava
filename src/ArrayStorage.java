/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        if (size < storage.length) {
            storage[size++] = r;
        } else {
            System.out.println("Невозможно добавить, хранилище заполнено");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме не найдено");
        return null;
    }

    void delete(String uuid) {
        boolean resumeFound = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i - 1);
                size--;
                resumeFound = true;
                break;
            }
        }
        if (!resumeFound) {
            System.out.println("Резюме не найдено");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[size];
        System.arraycopy(storage, 0, resume, 0, size);
        return resume;
    }

    int size() {
        return size;
    }
}
