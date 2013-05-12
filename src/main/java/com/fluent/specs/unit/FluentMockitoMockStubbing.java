package com.fluent.specs.unit;

import static org.mockito.Mockito.when;

import org.mockito.stubbing.OngoingStubbing;

import com.fluent.specs.unit.AbstractSpec.InitialMocking;
import com.fluent.specs.unit.AbstractSpec.SubsequentMocking;

public class FluentMockitoMockStubbing<TYPE> implements InitialMocking<TYPE>, SubsequentMocking
{
	private final OngoingStubbing<TYPE> ongoingStubbbing;

	protected FluentMockitoMockStubbing(final OngoingStubbing<TYPE> ongoingStubbing)
	{
		this.ongoingStubbbing = ongoingStubbing;
	}

	public <ANOTHER_TYPE> InitialMocking<ANOTHER_TYPE> AND(final ANOTHER_TYPE stubReturn)
	{
		return new FluentMockitoMockStubbing<ANOTHER_TYPE>(when(stubReturn));
	}

	public SubsequentMocking RETURNS(final TYPE firstReturnedValue, final TYPE... other)
	{
		ongoingStubbbing.thenReturn(firstReturnedValue, other);

		return this;
	}

	public SubsequentMocking THROWS(final Throwable... throwables)
	{
		ongoingStubbbing.thenThrow(throwables);

		return this;
	}

}