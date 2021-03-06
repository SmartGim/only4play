/*
 * This file is generated by jOOQ.
 */
package com.only4play.auth.db;


import com.only4play.auth.db.tables.AuthAdminUser;
import com.only4play.auth.db.tables.AuthResource;
import com.only4play.auth.db.tables.AuthRoleResource;
import com.only4play.auth.db.tables.AuthSysUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Authserver extends SchemaImpl {

    private static final long serialVersionUID = -1262766163;

    /**
     * The reference instance of <code>authserver</code>
     */
    public static final Authserver AUTHSERVER = new Authserver();

    /**
     * The table <code>authserver.auth_admin_user</code>.
     */
    public final AuthAdminUser AUTH_ADMIN_USER = com.only4play.auth.db.tables.AuthAdminUser.AUTH_ADMIN_USER;

    /**
     * The table <code>authserver.auth_resource</code>.
     */
    public final AuthResource AUTH_RESOURCE = com.only4play.auth.db.tables.AuthResource.AUTH_RESOURCE;

    /**
     * The table <code>authserver.auth_role_resource</code>.
     */
    public final AuthRoleResource AUTH_ROLE_RESOURCE = com.only4play.auth.db.tables.AuthRoleResource.AUTH_ROLE_RESOURCE;

    /**
     * The table <code>authserver.auth_sys_user</code>.
     */
    public final AuthSysUser AUTH_SYS_USER = com.only4play.auth.db.tables.AuthSysUser.AUTH_SYS_USER;

    /**
     * No further instances allowed
     */
    private Authserver() {
        super("authserver", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            AuthAdminUser.AUTH_ADMIN_USER,
            AuthResource.AUTH_RESOURCE,
            AuthRoleResource.AUTH_ROLE_RESOURCE,
            AuthSysUser.AUTH_SYS_USER);
    }
}
