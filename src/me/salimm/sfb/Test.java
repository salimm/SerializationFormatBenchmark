package me.salimm.sfb;

import java.io.InputStream;

import org.codehaus.jackson.JsonFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.salimm.sfb.formats.CBORSerializer;
import me.salimm.sfb.formats.JSONSerializer;

public class Test {
	public static void main(String[] args) throws Exception {
		CBORSerializer ser = new CBORSerializer();
		double[] in = new double[1000000*10];
		for (int i = 0; i < in.length; i++) {
			in[i] = 243546789;
		}

		byte[] data = ser.serialize(in, double[].class);

		System.out.println(in.length);
		System.out.println(data.length);
		System.out.println(new String(data).length());

		JSONSerializer jser = new JSONSerializer();
		byte[] data2 = jser.serialize(new String(data), String.class);
		
		System.out.println(new String(data).substring(0,10));
		System.out.println(new String(data2).substring(0,10));

		System.out.println(data2.length);

		String str = jser.deserialize(data2, String.class);
		System.out.println(str.length());

		double[] out = ser.deserialize(str.getBytes(), double[].class);

		System.out.println(out.length);

		System.out.println(out[0]);

	}
}
