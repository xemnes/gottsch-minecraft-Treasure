/**
 * 
 */
package com.someguyssoftware.treasure2.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author Mark Gottschling on Sep 13, 2014
 *
 */
public enum Gems implements IEnum {
	ONYX(0, "Onyx"),
	CHARMED_RUBY(1, "Charmed Ruby"),
	CHARMED_SAPPHIRE(2, "Charmed Sapphire"),
	ANGEL_BLESSED(3, "Angel Blessed Gem"),
	MINERS_FRIEND(4, "Miners Friend")
	;

	private static final Map<Integer, IEnum> codes = new HashMap<Integer, IEnum>();
	private static final Map<String, IEnum> values = new HashMap<String, IEnum>();
	private Integer code;
	private String value;

	// setup reverse lookup
	static {
		for (Gems ps : EnumSet.allOf(Gems.class)) {
			codes.put(ps.getCode(), ps);
			values.put(ps.getValue(), ps);
		}
	}

	/**
	 * Full constructor
	 * @param code
	 * @param value
	 */
	Gems(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	@Override
	public String getName() {
		return name();
	}
	
	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public void setCode(Integer code) {
		this.code = code;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public static Gems getByCode(Integer code) {
		return (Gems) codes.get(code);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Gems getByValue(String value) {
		return (Gems) values.get(value);
	}

	@Override
	public Map<Integer, IEnum> getCodes() {
		return codes;
	}
	@Override
	public Map<String, IEnum> getValues() {
		return values;
	}
}
