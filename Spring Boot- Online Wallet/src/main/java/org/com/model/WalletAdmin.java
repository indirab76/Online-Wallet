package org.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WalletAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="AdminId")
	private int adminId;
	
	@Column(name="AdminName")
	private String adminName;
	
	@Column(name="AdminPassword")
	private String password;
	
	@Column(name="AadhaarNo")
	private long aadhaarNo;
	
	@Column(name="PhoneNumber")
	private long phoneNumber;
	
	@Column(name="LoginName")
	private String loginName;

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(long aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return "WalletAdmin [adminId=" + adminId + ", adminName=" + adminName + ", password=" + password
				+ ", aadhaarNo=" + aadhaarNo + ", phoneNumber=" + phoneNumber + ", loginName=" + loginName + "]";
	}


	
}
