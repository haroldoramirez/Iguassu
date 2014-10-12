package br.com.emanuelvictor.iguassu.web.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;
@JsonIgnoreProperties({"new"})
public abstract class SpringData<id extends Serializable> extends AbstractPersistable<id> implements Serializable {

	private static final long serialVersionUID = -8175815837393391240L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
