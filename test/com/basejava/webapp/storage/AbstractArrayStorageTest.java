package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.basejava.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        storage.clear();
        for (int i = 0; i < STORAGE_LIMIT; i++) {
            try {
                storage.save(new Resume("uuid"));
            } catch (StorageException e) {
                Assertions.fail("Storage overflow happened too early");
            }
        }
        Assertions.assertThrows(StorageException.class, () -> {
            storage.save(new Resume("uuid"));
        });
    }
}