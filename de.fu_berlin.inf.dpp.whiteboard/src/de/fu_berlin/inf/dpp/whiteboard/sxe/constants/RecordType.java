package de.fu_berlin.inf.dpp.whiteboard.sxe.constants;

/**
 * <p>
 * Enumeration for different instances of records.
 * </p>
 * 
 * <p>
 * Each constant is enhanced by its XML tag to be used when serialized.
 * Accessible by the toString()-method.</br>
 * 
 * The static utility method fromString(String) facilitates deserialization.
 * </p>
 * 
 * @author jurke
 * 
 */
public enum RecordType {

    NEW("new"), SET("set"), REMOVE("remove");

    private String name;

    private RecordType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static RecordType fromString(String raw) {
        for (RecordType t : RecordType.values())
            if (t.toString().equals(raw))
                return t;
        return null;
    }
}
