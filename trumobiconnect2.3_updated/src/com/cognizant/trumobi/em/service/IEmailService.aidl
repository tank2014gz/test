/*
 * Copyright (C) 2008-2009 Marc Blank
 * Licensed to The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognizant.trumobi.em.service;

import com.cognizant.trumobi.em.service.IEmailServiceCallback;
import android.os.Bundle;

interface IEmailService {
    int validate(in String protocol, in String host, in String userName, in String password,
        int port, boolean ssl, boolean trustCertificates) ;

    void startSync(long mailboxId);
    void stopSync(long mailboxId);

    void loadMore(long messageId);
    void loadAttachment(long attachmentId, String destinationFile, String contentUriString);

    void updateFolderList(long accountId);

    boolean createFolder(long accountId, String name);
    boolean deleteFolder(long accountId, String name);
    boolean renameFolder(long accountId, String oldName, String newName);

    void setCallback(IEmailServiceCallback cb);

    void setLogging(int on);

    void hostChanged(long accountId);

    Bundle autoDiscover(String userName, String password);

    void sendMeetingResponse(long messageId, int response);
    
    boolean sendOofSettings(long accountId, boolean enable, String replyMessage,
				String startDate, String endDate);
	boolean getOofSettings(long accountId);
}