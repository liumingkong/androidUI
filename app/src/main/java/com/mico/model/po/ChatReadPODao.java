package com.mico.model.po;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.mico.model.po.ChatReadPO;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CHAT_READ_PO.
*/
public class ChatReadPODao extends AbstractDao<ChatReadPO, String> {

    public static final String TABLENAME = "CHAT_READ_PO";

    /**
     * Properties of entity ChatReadPO.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property MsgId = new Property(0, String.class, "msgId", true, "MSG_ID");
        public final static Property ConvId = new Property(1, long.class, "convId", false, "CONV_ID");
        public final static Property Ctime = new Property(2, long.class, "ctime", false, "CTIME");
        public final static Property Ext = new Property(3, String.class, "ext", false, "EXT");
    };


    public ChatReadPODao(DaoConfig config) {
        super(config);
    }
    
    public ChatReadPODao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CHAT_READ_PO' (" + //
                "'MSG_ID' TEXT PRIMARY KEY NOT NULL ," + // 0: msgId
                "'CONV_ID' INTEGER NOT NULL ," + // 1: convId
                "'CTIME' INTEGER NOT NULL ," + // 2: ctime
                "'EXT' TEXT);"); // 3: ext
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_CHAT_READ_PO_MSG_ID ON CHAT_READ_PO" +
                " (MSG_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_CHAT_READ_PO_CONV_ID ON CHAT_READ_PO" +
                " (CONV_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_CHAT_READ_PO_CTIME ON CHAT_READ_PO" +
                " (CTIME);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CHAT_READ_PO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ChatReadPO entity) {
        stmt.clearBindings();
 
        String msgId = entity.getMsgId();
        if (msgId != null) {
            stmt.bindString(1, msgId);
        }
        stmt.bindLong(2, entity.getConvId());
        stmt.bindLong(3, entity.getCtime());
 
        String ext = entity.getExt();
        if (ext != null) {
            stmt.bindString(4, ext);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ChatReadPO readEntity(Cursor cursor, int offset) {
        ChatReadPO entity = new ChatReadPO( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // msgId
            cursor.getLong(offset + 1), // convId
            cursor.getLong(offset + 2), // ctime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // ext
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ChatReadPO entity, int offset) {
        entity.setMsgId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setConvId(cursor.getLong(offset + 1));
        entity.setCtime(cursor.getLong(offset + 2));
        entity.setExt(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(ChatReadPO entity, long rowId) {
        return entity.getMsgId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(ChatReadPO entity) {
        if(entity != null) {
            return entity.getMsgId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}