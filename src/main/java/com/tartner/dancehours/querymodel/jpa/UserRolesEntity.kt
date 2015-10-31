package com.tartner.dancehours.querymodel.jpa

import com.google.common.base.Objects
import com.tartner.utilities.emptyUUID
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user_roles", schema = "public", catalog = "dance_hours")
public class UserRolesEntity {
    @Id @Type(type = "pg-uuid")
    @Column(name = "role_id", nullable = false, insertable = true, updatable = true)
    public var roleId: UUID = emptyUUID()

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public var name: String? = null

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true,
        length = 2147483647)
    public var description: String? = null

    @Basic
    @Column(name = "is_admin", nullable = true, insertable = true, updatable = true)
    public var isAdmin: Boolean? = null
    override fun hashCode(): Int {
        return Objects.hashCode(roleId, name, description, isAdmin)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) { return true }
        if (o == null || javaClass != o.javaClass) { return false }
        val that = o as UserRolesEntity
        return Objects.equal(roleId, that.roleId) && Objects.equal(name,
            that.name) && Objects.equal(description, that.description) && Objects.equal(isAdmin,
            that.isAdmin)
    }
}
