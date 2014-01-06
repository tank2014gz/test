

package com.cognizant.trumobi.em.mail.store.imap;

import com.cognizant.trumobi.em.mail.EmStore;

public final class EmImapConstants {
    private EmImapConstants() {}

    public static final String FETCH_FIELD_BODY_PEEK_BARE = "BODY.PEEK";
    public static final String FETCH_FIELD_BODY_PEEK = FETCH_FIELD_BODY_PEEK_BARE + "[]";
    public static final String FETCH_FIELD_BODY_PEEK_SANE
            = String.format("BODY.PEEK[]<0.%d>", EmStore.FETCH_BODY_SANE_SUGGESTED_SIZE);
    public static final String FETCH_FIELD_HEADERS =
            "BODY.PEEK[HEADER.FIELDS (date subject from content-type to cc message-id)]";

    public static final String ALERT = "ALERT";
    public static final String APPEND = "APPEND";
    public static final String BAD = "BAD";
    public static final String BADCHARSET = "BADCHARSET";
    public static final String BODY = "BODY";
    public static final String BODY_BRACKET_HEADER = "BODY[HEADER";
    public static final String BODYSTRUCTURE = "BODYSTRUCTURE";
    public static final String BYE = "BYE";
    public static final String CAPABILITY = "CAPABILITY";
    public static final String CHECK = "CHECK";
    public static final String CLOSE = "CLOSE";
    public static final String COPY = "COPY";
    public static final String CREATE = "CREATE";
    public static final String DELETE = "DELETE";
    public static final String EXAMINE = "EXAMINE";
    public static final String EXISTS = "EXISTS";
    public static final String EXPUNGE = "EXPUNGE";
    public static final String FETCH = "FETCH";
    public static final String FLAG_ANSWERED = "\\ANSWERED";
    public static final String FLAG_DELETED = "\\DELETED";
    public static final String FLAG_FLAGGED = "\\FLAGGED";
    public static final String FLAG_NO_SELECT = "\\NOSELECT";
    public static final String FLAG_SEEN = "\\SEEN";
    public static final String FLAGS = "FLAGS";
    public static final String FLAGS_SILENT = "FLAGS.SILENT";
    public static final String ID = "ID";
    public static final String INBOX = "INBOX";
    public static final String INTERNALDATE = "INTERNALDATE";
    public static final String LIST = "LIST";
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String LSUB = "LSUB";
    public static final String NO = "NO";
    public static final String NOOP = "NOOP";
    public static final String OK = "OK";
    public static final String PARSE = "PARSE";
    public static final String PERMANENTFLAGS = "PERMANENTFLAGS";
    public static final String PREAUTH = "PREAUTH";
    public static final String READ_ONLY = "READ-ONLY";
    public static final String READ_WRITE = "READ-WRITE";
    public static final String RENAME = "RENAME";
    public static final String RFC822_SIZE = "RFC822.SIZE";
    public static final String SEARCH = "SEARCH";
    public static final String SELECT = "SELECT";
    public static final String STARTTLS = "STARTTLS";
    public static final String STATUS = "STATUS";
    public static final String STORE = "STORE";
    public static final String SUBSCRIBE = "SUBSCRIBE";
    public static final String TEXT = "TEXT";
    public static final String TRYCREATE = "TRYCREATE";
    public static final String UID = "UID";
    public static final String UID_COPY = "UID COPY";
    public static final String UID_FETCH = "UID FETCH";
    public static final String UID_SEARCH = "UID SEARCH";
    public static final String UID_STORE = "UID STORE";
    public static final String UIDNEXT = "UIDNEXT";
    public static final String UIDVALIDITY = "UIDVALIDITY";
    public static final String UNSEEN = "UNSEEN";
    public static final String UNSUBSCRIBE = "UNSUBSCRIBE";
    public static final String APPENDUID = "APPENDUID";
    public static final String NIL = "NIL";
}
