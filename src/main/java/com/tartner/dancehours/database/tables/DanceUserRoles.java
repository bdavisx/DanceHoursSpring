/**
 * This class is generated by jOOQ
 */
package com.tartner.dancehours.database.tables;

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
public class DanceUserRoles extends org.jooq.impl.TableImpl<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord> {

	private static final long serialVersionUID = 726337915;

	/**
	 * The reference instance of <code>public.dance_user_roles</code>
	 */
	public static final com.tartner.dancehours.database.tables.DanceUserRoles DANCE_USER_ROLES = new com.tartner.dancehours.database.tables.DanceUserRoles();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord> getRecordType() {
		return com.tartner.dancehours.database.tables.records.DanceUserRolesRecord.class;
	}

	/**
	 * The column <code>public.dance_user_roles.user_id</code>.
	 */
	public final org.jooq.TableField<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord, java.util.UUID> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

	/**
	 * The column <code>public.dance_user_roles.role_id</code>.
	 */
	public final org.jooq.TableField<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord, java.lang.Integer> ROLE_ID = createField("role_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * Create a <code>public.dance_user_roles</code> table reference
	 */
	public DanceUserRoles() {
		this("dance_user_roles", null);
	}

	/**
	 * Create an aliased <code>public.dance_user_roles</code> table reference
	 */
	public DanceUserRoles(java.lang.String alias) {
		this(alias, com.tartner.dancehours.database.tables.DanceUserRoles.DANCE_USER_ROLES);
	}

	private DanceUserRoles(java.lang.String alias, org.jooq.Table<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord> aliased) {
		this(alias, aliased, null);
	}

	private DanceUserRoles(java.lang.String alias, org.jooq.Table<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.tartner.dancehours.database.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord> getPrimaryKey() {
		return com.tartner.dancehours.database.Keys.DANCE_USER_ROLES_PKEY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<com.tartner.dancehours.database.tables.records.DanceUserRolesRecord>>asList(com.tartner.dancehours.database.Keys.DANCE_USER_ROLES_PKEY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.tartner.dancehours.database.tables.DanceUserRoles as(java.lang.String alias) {
		return new com.tartner.dancehours.database.tables.DanceUserRoles(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.tartner.dancehours.database.tables.DanceUserRoles rename(java.lang.String name) {
		return new com.tartner.dancehours.database.tables.DanceUserRoles(name, null);
	}
}
