/*
* JBoss, Home of Professional Open Source.
* Copyright 2012, Red Hat Middleware LLC, and individual contributors
* as indicated by the @author tags. See the copyright.txt file in the
* distribution for a full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.wildfly.legacy.test.controller.core_10_0_0;

import org.jboss.as.controller.ControlledProcessState;
import org.jboss.as.controller.ProcessType;
import org.jboss.as.controller.RunningModeControl;
import org.jboss.as.controller.extension.ExtensionRegistry;
import org.jboss.as.core.model.test.ModelInitializer;
import org.jboss.as.core.model.test.TestModelType;
import org.jboss.as.model.test.ModelTestModelControllerService;
import org.jboss.as.model.test.ModelTestOperationValidatorFilter;
import org.jboss.as.model.test.StringConfigurationPersister;
import org.jboss.as.repository.ContentRepository;
import org.jboss.msc.value.InjectedValue;
import org.wildfly.legacy.test.spi.core.TestModelControllerFactory;

/**
 *
 * @author Tomaz Cerar
 */
public class TestModelControllerFactory10_0_0 implements TestModelControllerFactory {

    @Override
    public ModelTestModelControllerService create(ProcessType processType, RunningModeControl runningModeControl,
            StringConfigurationPersister persister, ModelTestOperationValidatorFilter validateOpsFilter, TestModelType type,
            ModelInitializer modelInitializer, ExtensionRegistry extensionRegistry) {
        ControlledProcessState processState = new ControlledProcessState(true);
        return new TestModelControllerService10_0_0(processType, runningModeControl, persister, validateOpsFilter, type,
                modelInitializer, new TestModelControllerService10_0_0.DelegatingResourceDefinition(type), processState, extensionRegistry);
    }

    @Override
    public InjectedValue<ContentRepository> getContentRepositoryInjector(ModelTestModelControllerService service) {
        return ((TestModelControllerService10_0_0)service).getContentRepositoryInjector();
    }
}
