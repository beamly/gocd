/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.config.materials;

import com.thoughtworks.go.config.ConfigCollection;
import com.thoughtworks.go.config.ConfigTag;
import com.thoughtworks.go.config.Validatable;
import com.thoughtworks.go.config.ValidationContext;
import com.thoughtworks.go.domain.ConfigErrors;

import java.util.LinkedHashSet;
import java.util.List;

@ConfigTag("authorFilter")
@ConfigCollection(IgnoredAuthors.class)
public class AuthorFilter extends LinkedHashSet<IgnoredAuthors> implements Validatable {
    private ConfigErrors configErrors = new ConfigErrors();

    public AuthorFilter() {
    }

    public AuthorFilter(IgnoredAuthors... ignores) {
        for (IgnoredAuthors ignore : ignores) {
            this.add(ignore);
        }
    }

    public AuthorFilter(List<IgnoredAuthors> ignores) {
        super(ignores);
    }

    public static AuthorFilter create(String... authors) {
        AuthorFilter f = new AuthorFilter();
        for (String author : authors) {
            f.add(new IgnoredAuthors(author));
        }
        return f;        
    }

    public String getStringForDisplay() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder display = new StringBuilder();
        for (IgnoredAuthors ignoredAuthors : this) {
            display.append(ignoredAuthors.getPattern()).append(",");
        }
        return display.substring(0, display.length() - 1);
    }

    public static AuthorFilter fromDisplayString(String displayString) {
        AuthorFilter filter = new AuthorFilter();
        String[] ignoredPatterns = displayString.split(",");
        for (String ignoredPattern : ignoredPatterns) {
            filter.add(new IgnoredAuthors(ignoredPattern.trim()));
        }
        return filter;
    }

    public void validate(ValidationContext validationContext) {
    }

    public ConfigErrors errors() {
        return configErrors;
    }

    public void addError(String fieldName, String message) {
        configErrors.add(fieldName, message);
    }

    public boolean shouldNeverIgnore() {
        return false;
    }
}
