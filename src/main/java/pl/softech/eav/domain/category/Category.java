/*
 * Copyright 2013 Sławomir Śledź <slawomir.sledz@sof-tech.pl>.
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
package pl.softech.eav.domain.category;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import pl.softech.eav.domain.AbstractEntity;
import pl.softech.eav.domain.TextMedium;

/**
 * @author Sławomir Śledź <slawomir.sledz@sof-tech.pl>
 * @since 1.0
 */
@Entity
@Table(name = "category")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends AbstractEntity {

	@Valid
	@Embedded
	@AttributeOverride(name = CategoryIdentifier.IDENTIFIER_PROPERTY, column = @Column(nullable = false, unique = true))
	private CategoryIdentifier identifier;

	@TextMedium
	@Column(nullable = false)
	private String name;
	
	protected Category() {
	}
	
	public Category(Builder builder) {
		this(new CategoryIdentifier(builder.identifier), builder.name);
	}

	public Category(CategoryIdentifier identifier, String name) {
		this.identifier = checkNotNull(identifier, ARG_NOT_NULL_CHECK, "identifier");
		this.name = checkNotNull(name, ARG_NOT_NULL_CHECK, "name");
	}
	
	public CategoryIdentifier getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		sb.appendSuper(super.toString());
		sb.append("identifier", identifier);
		sb.append("name", name);
		return sb.toString();
	}
	
	public static class Builder {
		
		private String identifier;
		private String name;
		
		public Builder withIdentifier(String identifier) {
			this.identifier = identifier;
			return this;
		}
		public Builder withName(String name) {
			this.name = name;
			return this;
		}
		
	}

}
