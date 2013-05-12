package com.fluent.specs.unit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;

import org.junit.Test;

import com.fluent.specs.UnitUnderTest;

@SuppressWarnings("unused")
public class SpyingBehaviour
{
	static final String ANY_RETURN = "DOES SOMETHING ELSE INSTEAD";
	static final String ANY_OTHER_RETURN = "DOES SOMETHING ELSE INSTEAD A SECOND TIME";
	static final int ANY_PARAMETER = 3;

	UnitUnderTest unit;

	String integerOutcome;
	RuntimeException exceptionOutcome;
	RuntimeException exceptionToBeThrown;

	@Test public void should_stub_out_method_to_return_value() throws Exception
	{
		CONTEXT: unit = spy(new UnitUnderTest());
		CONTEXT: new FluentMockitoSpyStubbing<UnitUnderTest>(unit).RETURNS(ANY_RETURN).ON().doSomething();

		TRIGGER: integerOutcome = unit.doSomething();

		OUTCOME: assertThat(integerOutcome, is(equalTo(ANY_RETURN)));
	}

	@Test public void should_stub_out_method_to_throw_exception() throws Exception
	{
		CONTEXT: unit = spy(new UnitUnderTest());
		CONTEXT: exceptionToBeThrown = new RuntimeException();
		CONTEXT: new FluentMockitoSpyStubbing<UnitUnderTest>(unit).THROWS(exceptionToBeThrown).ON().doSomething();

		TRIGGER: try
		{
			unit.doSomething();
		}
		catch (final RuntimeException throwable)
		{
			exceptionOutcome = throwable;
		}

		OUTCOME: assertThat(exceptionOutcome, is(equalTo(exceptionToBeThrown)));
	}
}
