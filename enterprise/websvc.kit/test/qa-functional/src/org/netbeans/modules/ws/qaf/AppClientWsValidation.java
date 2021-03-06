/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.ws.qaf;

import java.io.IOException;
import org.netbeans.jellytools.EditorOperator;
import org.netbeans.modules.ws.qaf.WebServicesTestBase.ProjectType;

/**
 *
 * @author jp154641
 */
public class AppClientWsValidation extends EjbWsValidation {

    public AppClientWsValidation(String name) {
        super(name);
    }

    @Override
    protected ProjectType getProjectType() {
        return ProjectType.APPCLIENT;
    }

    @Override
    protected String getWsClientProjectName() {
        return "WsClientInAppClient"; //NOI18N
    }

    /**
     * Tests Call Web Service Operation action in a servlet
     */
    public void testCallWsOperationInJavaMainClass() {
        final EditorOperator eo = new EditorOperator("Main.java"); //NOI18N
        eo.select("// TODO code application logic here"); //NOI18N
        callWsOperation(eo, "myIntMethod", eo.getLineNumber()); //NOI18N
        assertTrue("@WebServiceRef has not been found", eo.contains("@WebServiceRef")); //NOI18N
        assertFalse("Lookup found", eo.contains(getWsClientLookupCall())); //NOI18N
    }

    /**
     * Run project
     * @throws java.io.IOException
     */
    public void testRunWsClientProject() throws IOException {
        runProject(getProjectName());
    }

    public void testUndeployClientProject() throws IOException {
        undeployProject(getProjectName());
    }
}
