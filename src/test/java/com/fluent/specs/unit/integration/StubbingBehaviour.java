package com.fluent.specs.unit.integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Test;

import com.fluent.specs.Collaborator;
import com.fluent.specs.UnitUnderTest;
import com.fluent.specs.unit.AbstractSpec;

public class StubbingBehaviour
{
	AbstractSpec specs = new AbstractSpec(){};
	UnitUnderTest spy = spy(new UnitUnderTest());
	Collaborator mock = mock(Collaborator.class);
	int integerOutcome;
	String stringOutcome;

	static final int ANY_RETURN = 1;
	static final int ANY_OTHER_RETURN = 2;
	static final int ANY_PARAMETER = 3;

	@Test public void should_stub_out_method_on_mock() throws Exception
	{
		specs.GIVEN(mock.doSomethingWith(ANY_PARAMETER)).RETURNS(ANY_RETURN);

		integerOutcome = mock.doSomethingWith(ANY_PARAMETER);

		assertThat(integerOutcome, is(equalTo(ANY_RETURN)));
	}

	@Test public void should_stub_out_method_on_spy() throws Exception
	{
		specs.GIVEN_SPY(spy).RETURNS("DOES SOMETHING ELSE").ON().doSomething();

		stringOutcome = spy.doSomething();

		assertThat(stringOutcome, is(equalTo("DOES SOMETHING ELSE")));
	}
}
