/**
 * This class is generated by jOOQ
 */
package com.tartner.dancehours.querymodel.database.tables.pojos;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.4"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DanceUser implements java.io.Serializable {

	private static final long serialVersionUID = 1617022212;

	private java.util.UUID    userId;
	private java.lang.String  email;
	private java.lang.String  firstName;
	private java.lang.String  lastName;
	private java.lang.Boolean isActive;
	private java.lang.String  userType;

	public DanceUser() {}

	public DanceUser(
		java.util.UUID    userId,
		java.lang.String  email,
		java.lang.String  firstName,
		java.lang.String  lastName,
		java.lang.Boolean isActive,
		java.lang.String  userType
	) {
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isActive = isActive;
		this.userType = userType;
	}

	public java.util.UUID getUserId() {
		return this.userId;
	}

	public DanceUser setUserId(java.util.UUID userId) {
		this.userId = userId;
		return this;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public DanceUser setEmail(java.lang.String email) {
		this.email = email;
		return this;
	}

	public java.lang.String getFirstName() {
		return this.firstName;
	}

	public DanceUser setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
		return this;
	}

	public java.lang.String getLastName() {
		return this.lastName;
	}

	public DanceUser setLastName(java.lang.String lastName) {
		this.lastName = lastName;
		return this;
	}

	public java.lang.Boolean getIsActive() {
		return this.isActive;
	}

	public DanceUser setIsActive(java.lang.Boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	public java.lang.String getUserType() {
		return this.userType;
	}

	public DanceUser setUserType(java.lang.String userType) {
		this.userType = userType;
		return this;
	}
}