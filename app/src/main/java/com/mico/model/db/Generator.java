package com.mico.model.db;

import com.mico.constants.ModelConstants;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by zhoumingjun on 3/17/14.
 */
public class Generator {
    public static final String SCHEMA_NAME = "com.mico.model.po";
    public static final int SCHEMA_VERSION = 6;

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(SCHEMA_VERSION, SCHEMA_NAME);
        addUserProfile(schema);
        addConversation(schema);
        addChatMessage(schema);
        addGroupProfile(schema);
        addSetting(schema);
        addRelation(schema);
        addChatRead(schema);
        addTranslate(schema);
        addPushData(schema);
        addStickerData(schema);
        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    private static void addUserProfile(Schema schema) {
        Entity userProfile = schema.addEntity("UserProfilePO");
        userProfile.addLongProperty(ModelConstants.UID).primaryKey().index();
        userProfile.addIntProperty(ModelConstants.GENDAR).notNull();
        userProfile.addStringProperty(ModelConstants.DISPLAY_NAME).notNull();
        userProfile.addStringProperty(ModelConstants.AVATAR);
        userProfile.addStringProperty(ModelConstants.DESCRIPTION);
        userProfile.addLongProperty(ModelConstants.CREATE_TIME);
        userProfile.addLongProperty(ModelConstants.BIRTHDAY);
        userProfile.addIntProperty(ModelConstants.LEVEL);
        userProfile.addStringProperty(ModelConstants.EXTEND);
    }

    private static void addConversation(Schema schema) {
        Entity conv = schema.addEntity("ConversationPO");
        conv.addLongProperty("convId").primaryKey().index();
        conv.addIntProperty("type").index().notNull();
        conv.addStringProperty("lastMessageId"); //最后一次更新的时间
        conv.addLongProperty("lastUpdateTime").index().notNull(); // 单人聊天为信息摘要，群聊为 发送者:信息内容
        conv.addStringProperty("lastUpdateMessage");
        conv.addIntProperty("unreadCount"); // 当前群的未读数
        conv.addIntProperty("lastUpdateStatus"); // last update status
        conv.addStringProperty("convSetting"); // 会话设置
        conv.addStringProperty("ext");
    }

    private static void addChatMessage(Schema schema) {
        Entity ChatMessage = schema.addEntity("ChatMessagePO");
        ChatMessage.addStringProperty("msgId").primaryKey().index();
        ChatMessage.addLongProperty("convId").index().notNull();
        ChatMessage.addLongProperty("uid").index().notNull();
        ChatMessage.addIntProperty("direction").notNull();
        ChatMessage.addIntProperty("type");
        ChatMessage.addIntProperty("status");
        ChatMessage.addLongProperty("ctime").index().notNull();
        ChatMessage.addStringProperty("ext");
    }

    private static void addGroupProfile(Schema schema) {
        Entity relation = schema.addEntity("GroupProfilePO");
        relation.addLongProperty(ModelConstants.GROUPID).primaryKey().index(); // 群ID
        relation.addIntProperty(ModelConstants.GROUP_TYPE); // 群类型
        relation.addLongProperty(ModelConstants.GROUP_OWNERID);   // 群主
        relation.addStringProperty(ModelConstants.GROUP_NAME); // 群名称
        relation.addStringProperty(ModelConstants.AVATAR);    // 群头像
        relation.addStringProperty(ModelConstants.DESCRIPTION); // 群简介
        relation.addIntProperty(ModelConstants.GROUP_LEVEL);    // 群等级
        relation.addStringProperty(ModelConstants.GROUP_MEMBERS); // 群成员
        relation.addStringProperty(ModelConstants.GROUP_LOCATION); // 群地址描述
        relation.addIntProperty(ModelConstants.GROUP_PRIVACY); // 群隐私
        relation.addStringProperty(ModelConstants.EXTEND);    // 扩展信息
        relation.addLongProperty(ModelConstants.CREATE_TIME);  // 群创建信息
    }

    private static void addSetting(Schema schema) {
        Entity relation = schema.addEntity("SettingPO");
        relation.addStringProperty("key").primaryKey().notNull().index();
        relation.addStringProperty("value");
    }

    private static void addRelation(Schema schema) {
        Entity userProfile = schema.addEntity("RelationPO");
        userProfile.addLongProperty(ModelConstants.UID).primaryKey().index();
        userProfile.addIntProperty(ModelConstants.TYPE).notNull().index();
        userProfile.addLongProperty(ModelConstants.LASTUPDATE).notNull().index();
    }

    private static void addChatRead(Schema schema) {
        Entity chatRead = schema.addEntity("ChatReadPO");
        chatRead.addStringProperty("msgId").primaryKey().index();
        chatRead.addLongProperty("convId").index().notNull();
        chatRead.addLongProperty("ctime").index().notNull();
        chatRead.addStringProperty("ext");
    }

    private static void addTranslate(Schema schema) {
        Entity translate = schema.addEntity("TranslatePO");
        translate.addIdProperty().primaryKey();
        translate.addStringProperty("translateIndex").index().notNull();
        translate.addStringProperty("originText").index().notNull();
        translate.addStringProperty("translateText").index().notNull();
        translate.addStringProperty("locale").notNull();
        translate.addIntProperty("type");
        translate.addStringProperty("ext");
    }

    private static void addPushData(Schema schema) {
        Entity pushData = schema.addEntity("PushDataPO");
        pushData.addIdProperty().primaryKey();
        pushData.addIntProperty("type");
        pushData.addStringProperty("content").index().notNull();
        pushData.addLongProperty("ctime").index().notNull();
        pushData.addStringProperty("ext");
    }


    private static void addStickerData(Schema schema) {
        Entity stickerData = schema.addEntity("StickerData");
        stickerData.addStringProperty("stickerId").primaryKey().index();
        stickerData.addStringProperty("content").notNull();
        stickerData.addLongProperty("ctime").index().notNull();
        stickerData.addStringProperty("ext");
    }
}
