/*
 * This file is generated by jOOQ.
 */
package com.only4play.auth.db;


import com.only4play.auth.db.tables.AuthAdminUser;
import com.only4play.auth.db.tables.AuthResource;
import com.only4play.auth.db.tables.AuthRoleResource;
import com.only4play.auth.db.tables.AuthSysUser;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>authserver</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index AUTH_ADMIN_USER_PRIMARY = Indexes0.AUTH_ADMIN_USER_PRIMARY;
    public static final Index AUTH_RESOURCE_PRIMARY = Indexes0.AUTH_RESOURCE_PRIMARY;
    public static final Index AUTH_ROLE_RESOURCE_PRIMARY = Indexes0.AUTH_ROLE_RESOURCE_PRIMARY;
    public static final Index AUTH_SYS_USER_PRIMARY = Indexes0.AUTH_SYS_USER_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index AUTH_ADMIN_USER_PRIMARY = Internal.createIndex("PRIMARY", AuthAdminUser.AUTH_ADMIN_USER, new OrderField[] { AuthAdminUser.AUTH_ADMIN_USER.ID }, true);
        public static Index AUTH_RESOURCE_PRIMARY = Internal.createIndex("PRIMARY", AuthResource.AUTH_RESOURCE, new OrderField[] { AuthResource.AUTH_RESOURCE.ID }, true);
        public static Index AUTH_ROLE_RESOURCE_PRIMARY = Internal.createIndex("PRIMARY", AuthRoleResource.AUTH_ROLE_RESOURCE, new OrderField[] { AuthRoleResource.AUTH_ROLE_RESOURCE.ID }, true);
        public static Index AUTH_SYS_USER_PRIMARY = Internal.createIndex("PRIMARY", AuthSysUser.AUTH_SYS_USER, new OrderField[] { AuthSysUser.AUTH_SYS_USER.ID }, true);
    }
}
