package com.fluent.specs.unit;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import com.fluent.specs.unit.AbstractSpec.SpiedMethod;
import com.fluent.specs.unit.AbstractSpec.SpyExpectation;

public class FluentMockitoSpyStubbing<TYPE> implements SpyExpectation<TYPE>, SpiedMethod<TYPE>
{
	private Object returnValue;
	private final TYPE spy;
	private Throwable throwable;

	FluentMockitoSpyStubbing(final TYPE spy)
	{
		this.spy = spy;
	}

	public TYPE ON()
	{
		if (throwable != null)
		{
			doThrow(throwable).when(spy);
		}
		else
		{
			doReturn(returnValue).when(spy);
		}

		return spy;
	}

	public SpiedMethod<TYPE> RETURNS(final Object returnValue, final Object... otherReturnValues)
	{
		this.returnValue = returnValue;

		return this;
	}

	public SpiedMethod<TYPE> THROWS(final Exception throwable)
	{
		this.throwable = throwable;

		return this;
	}
}
