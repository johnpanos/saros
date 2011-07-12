package de.fu_berlin.inf.dpp.test.util;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.StorageException;

/**
 * @author Stefan Rossbach
 */
public class MemorySecurePreferences implements ISecurePreferences {

    private HashMap<String, Object> preferences = new HashMap<String, Object>();
    private boolean allowPut = true;
    private boolean allowGet = true;

    public void allowPutOperation(boolean allow) {
        this.allowPut = allow;
    }

    public void allowGetOperation(boolean allow) {
        this.allowGet = allow;
    }

    public void checkGetOperation() throws StorageException {
        if (!this.allowGet)
            throw new StorageException(StorageException.INTERNAL_ERROR,
                new IllegalStateException("get disabled"));
    }

    public void checkPutOperation() throws StorageException {
        if (!this.allowPut)
            throw new StorageException(StorageException.INTERNAL_ERROR,
                new IllegalStateException("put disabled"));
    }

    public String absolutePath() {
        throw new UnsupportedOperationException();
    }

    public String[] childrenNames() {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        preferences.clear();
    }

    public void flush() throws IOException {
        //
    }

    public String get(String key, String def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (String) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public boolean getBoolean(String key, boolean def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (Boolean) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public byte[] getByteArray(String key, byte[] def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (byte[]) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public double getDouble(String key, double def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (Double) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public float getFloat(String key, float def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (Float) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public int getInt(String key, int def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (Integer) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public long getLong(String key, long def) throws StorageException {
        checkGetOperation();

        Object object = preferences.get(key);

        if (object == null)
            return def;

        try {
            return (Long) object;
        } catch (Exception e) {
            throw new StorageException(StorageException.INTERNAL_ERROR, e);
        }
    }

    public boolean isEncrypted(String key) throws StorageException {
        return true;
    }

    public String[] keys() {
        return preferences.keySet().toArray(new String[0]);
    }

    public String name() {
        return this.getClass().getName();
    }

    public ISecurePreferences node(String name) {
        throw new UnsupportedOperationException();
    }

    public boolean nodeExists(String name) {
        throw new UnsupportedOperationException();
    }

    public ISecurePreferences parent() {
        throw new UnsupportedOperationException();
    }

    public void put(String key, String value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void putBoolean(String key, boolean value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void putByteArray(String key, byte[] value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void putDouble(String key, double value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void putFloat(String key, float value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void putInt(String key, int value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void putLong(String key, long value, boolean encrypt)
        throws StorageException {
        checkPutOperation();
        preferences.put(key, value);
    }

    public void remove(String key) {
        preferences.remove(key);
    }

    public void removeNode() {
        throw new UnsupportedOperationException();
    }
}
