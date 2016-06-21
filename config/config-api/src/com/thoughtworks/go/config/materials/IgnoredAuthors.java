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

import com.thoughtworks.go.config.ConfigAttribute;
import com.thoughtworks.go.config.ConfigTag;
import com.thoughtworks.go.config.Validatable;
import com.thoughtworks.go.config.ValidationContext;
import com.thoughtworks.go.domain.ConfigErrors;
import com.thoughtworks.go.domain.materials.MaterialConfig;
import com.thoughtworks.go.util.FileUtil;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.regex.Pattern;

@ConfigTag(value = "ignore")
public class IgnoredAuthors implements Serializable, Validatable {
    @ConfigAttribute(value = "pattern")
    private String pattern;
    private String processedPattern;
    private ConfigErrors configErrors = new ConfigErrors();
    private final Pattern punctuationRegex = Pattern.compile("\\p{Punct}");

    public static final String PATTERN = "pattern";
    public IgnoredAuthors() {
    }

    public IgnoredAuthors(String pattern) {
        this.pattern = pattern;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IgnoredAuthors ignore = (IgnoredAuthors) o;
        return !(pattern != null ? !pattern.equals(ignore.pattern) : ignore.pattern != null);
    }

    public int hashCode() {
        return (pattern != null ? pattern.hashCode() : 1);
    }

    public boolean shouldIgnore(MaterialConfig materialConfig, String name) {
        if (name == null) {
            return false;
        } else {
            return Pattern.compile(pattern).matcher(name).find();
        }
    }

    private String escape(String pattern) {
        StringBuilder result = new StringBuilder();
        for(char c : pattern.toCharArray()){
            if( c != '*' && punctuationRegex.matcher(String.valueOf(c)).matches()){
                result.append("\\").append(c);
            }else{
                result.append(String.valueOf(c));
            }
        }
        return result.toString();
    }

    public String toString() {
        return "The ignore pattern is [" + pattern + "]";
    }

    public String getPattern() {
        return pattern;
    }

    public void validate(ValidationContext validationContext) {
    }

    public ConfigErrors errors() {
        return configErrors;
    }

    public void addError(String fieldName, String message) {
        configErrors.add(fieldName, message);
    }
}
