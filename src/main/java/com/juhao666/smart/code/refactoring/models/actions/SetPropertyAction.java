/*
 * Copyright 2021 - 2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.juhao666.smart.code.refactoring.models.actions;

import com.juhao666.smart.code.refactoring.models.actions.AbstractAction;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@SuperBuilder
@NoArgsConstructor
public class SetPropertyAction extends AbstractAction {

    private String propertyName;
    private String propertyValue;

    @Override
    public void apply() {
        System.out.println("Update Java to 17 success!");
    }
//    public void apply(ProjectContext context) {
//        context.getBuildFile().setProperty(propertyName, propertyValue);
//    }
}
