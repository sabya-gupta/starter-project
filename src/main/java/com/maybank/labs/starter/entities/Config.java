package com.maybank.labs.starter.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIG")
public class Config implements Serializable {

	private static final long serialVersionUID = -3533439998057254115L;

	@Id
	@Column(name = "CONFIG_ID")
	private int configId;

	@Column(name = "CONFIG_ENTRY")
	private String configEntry;

	@Column(name = "CONFIG_VALUE")
	private String configValue;

	@Column(name = "CONFIG_SHORT_CODE")
	private String configShortCode;

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(final int configId) {
		this.configId = configId;
	}

	public String getConfigEntry() {
		return configEntry;
	}

	public void setConfigEntry(final String configEntry) {
		this.configEntry = configEntry;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(final String configValue) {
		this.configValue = configValue;
	}

	public String getConfigShortCode() {
		return configShortCode;
	}

	public void setConfigShortCode(final String configShortCode) {
		this.configShortCode = configShortCode;
	}

}
