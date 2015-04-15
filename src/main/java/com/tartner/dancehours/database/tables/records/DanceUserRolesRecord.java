/**
 * This class is generated by jOOQ
 */
package com.tartner.dancehours.database.tables.records;

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
public class DanceUserRolesRecord extends org.jooq.impl.UpdatableRecordImpl<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord> implements org.jooq.Record2<java.util.UUID, java.lang.Integer> {

	private static final long serialVersionUID = 412163851;

	/**
	 * Setter for <code>public.dance_user_roles.user_id</code>.
	 */
	public void setUserId(java.util.UUID value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>public.dance_user_roles.user_id</code>.
	 */
	public java.util.UUID getUserId() {
		return (java.util.UUID) getValue(0);
	}

	/**
	 * Setter for <code>public.dance_user_roles.role_id</code>.
	 */
	public void setRoleId(java.lang.Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>public.dance_user_roles.role_id</code>.
	 */
	public java.lang.Integer getRoleId() {
		return (java.lang.Integer) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record2<java.util.UUID, java.lang.Integer> key() {
		return (org.jooq.Record2) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.util.UUID, java.lang.Integer> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<java.util.UUID, java.lang.Integer> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.util.UUID> field1() {
		return com.tartner.dancehours.database.tables.DanceUserRoles.DANCE_USER_ROLES.USER_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field2() {
		return com.tartner.dancehours.database.tables.DanceUserRoles.DANCE_USER_ROLES.ROLE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.UUID value1() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value2() {
		return getRoleId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DanceUserRolesRecord value1(java.util.UUID value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DanceUserRolesRecord value2(java.lang.Integer value) {
		setRoleId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DanceUserRolesRecord values(java.util.UUID value1, java.lang.Integer value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DanceUserRolesRecord
	 */
	public DanceUserRolesRecord() {
		super(com.tartner.dancehours.database.tables.DanceUserRoles.DANCE_USER_ROLES);
	}

	/**
	 * Create a detached, initialised DanceUserRolesRecord
	 */
	public DanceUserRolesRecord(java.util.UUID userId, java.lang.Integer roleId) {
		super(com.tartner.dancehours.database.tables.DanceUserRoles.DANCE_USER_ROLES);

		setValue(0, userId);
		setValue(1, roleId);
	}
}
