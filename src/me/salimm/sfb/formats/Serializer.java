package me.salimm.sfb.formats;

public interface Serializer {

	public <T extends Object> byte[]  serialize(Object obj, Class<T> cls) throws Exception;

	public <T extends Object> Object deserialize(byte[] data, Class<T> cls) throws Exception;

	public FormatType getFormatType();

}
