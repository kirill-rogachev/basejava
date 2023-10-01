package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.*;

import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        Assertions.assertEquals(3, array.length);
        Assertions.assertEquals(RESUME_1, array[0]);
        Assertions.assertEquals(RESUME_2, array[1]);
        Assertions.assertEquals(RESUME_3, array[2]);
    }

    @Test
    public void save() {
        Resume Resume_4 = new Resume("uuid4");
        storage.save(Resume_4);
        Assertions.assertEquals(4, storage.size());
        Assertions.assertEquals(Resume_4, storage.get("uuid4"));
    }

    @Test
    public void saveExist() {
        Assertions.assertThrows(ExistStorageException.class, () -> {
            storage.save(RESUME_1);
        });
    }

    @Test
    public void storageOverflow() {
        for (int i = 3; i < STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("UUID_" + (i + 1)));
            } catch (StorageException e) {
                Assertions.fail("Storage overflow happened too early");
            }
        }
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume("UUID_10000"));
        });
    }

    @Test
    public void delete() {
        storage.delete(UUID_3);
        Assertions.assertEquals(2, storage.size());
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get(UUID_3);
        });
    }

    @Test
    public void deleteNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.delete("dummy");
        });
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assertions.assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    public void updateNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.update(new Resume("dummy"));
        });
    }

    @Test
    public void get() {
        Assertions.assertEquals(RESUME_1, storage.get("uuid1"));
        Assertions.assertEquals(RESUME_2, storage.get("uuid2"));
        Assertions.assertEquals(RESUME_3, storage.get("uuid3"));
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }
}